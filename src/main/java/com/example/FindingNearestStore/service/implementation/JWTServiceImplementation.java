package com.example.FindingNearestStore.service.implementation;

import com.example.FindingNearestStore.dto.PayLoadRequestDTO;
import com.example.FindingNearestStore.dataSource.MultitenantDataSource;
import com.example.FindingNearestStore.dataSource.TenantContext;
import com.example.FindingNearestStore.model.Company;
import com.example.FindingNearestStore.model.Plan_subscription_company_view;
import com.example.FindingNearestStore.model.Subscription;
import com.example.FindingNearestStore.repository.CompanyRepository;
import com.example.FindingNearestStore.repository.Plan_subscription_company_viewRepository;
import com.example.FindingNearestStore.repository.SubscriptionRepository;
import com.example.FindingNearestStore.service.JWTService;
import com.example.FindingNearestStore.service.SubscriptionService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

@Service
@Slf4j
public class JWTServiceImplementation implements JWTService {
    @Autowired
    SubscriptionRepository subscriptionRepository;

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    Plan_subscription_company_viewRepository planViewRepository;
    @Autowired
    SubscriptionService subscriptionService;
    @Autowired
    MultitenantDataSource multitenantDataSource;
    public static final String SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";


    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    @Override
    public String extractSubscriptionId(String token) {
        return extractClaim(token, claims -> claims.get("subscriptionId", String.class));
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public Claims extractAllClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }


    @Override
    public String generateToken(PayLoadRequestDTO payLoadRequestDTO) {

        Map<String, Object> claims = new HashMap<>();

        return createToken(claims, payLoadRequestDTO);

    }


    private String createToken(Map<String, Object> claims, PayLoadRequestDTO payLoadRequestDTO) {

//        Optional<Company> company= companyRepository.findBycompanyName(payLoadRequestDTO.companyName);


        log.info(TenantContext.getCurrentTenant());
        Optional<Subscription> subscriptions = subscriptionRepository.findBycompanyIdAndExpiryDateGreaterThan(payLoadRequestDTO.getCompanyId(), new Date());
        log.info("subscription:", subscriptions);

        String companyId = "";
//     Optional<Subscription>subscriptions= Optional.of(new Subscription());

        String subscriptionId = "";
        if (subscriptions.isEmpty()) {

            companyId = payLoadRequestDTO.getCompanyId();
        } else {
            companyId = subscriptions.get().getCompanyId();
            subscriptionId = subscriptions.get().getSubscriptionId();
        }
        return Jwts.builder().setClaims(claims).setSubject(payLoadRequestDTO.getCompanyName()).claim("companyId", companyId).claim("subscriptionId", subscriptionId).setIssuedAt(new Date(System.currentTimeMillis())).setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30)).signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails, PayLoadRequestDTO payLoadRequestDTO) {
        final String username = extractUsername(token);
        final String subscriptionId = extractSubscriptionId(token);
        Optional<Company> company = companyRepository.findBycompanyName(username);
        Optional<Plan_subscription_company_view> planView = planViewRepository.findBycompanyId(company.get().getCompanyId());
        if (company.isPresent()) {
            Plan_subscription_company_view plan_subscription_company_view = subscriptionService.getPlanDetailsByCompanyId(company.get().getCompanyId());

            if (plan_subscription_company_view != null && subscriptionService.isSubscriptionValid(plan_subscription_company_view) || subscriptionId != null) {
                Map<String, Object> claims = new HashMap<>();
//                    return createToken(claims,payLoadRequestDTcontentCachingResponseWrapper.copyBodyToResponse();O,company.get(),plan_subscription_company_view);

                return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
            } else {
                throw new RuntimeException("Plan or Subscription not found or expired");
            }


        } else {
            throw new RuntimeException("Company not found");
        }


    }


    private Key getSignKey() {

        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
