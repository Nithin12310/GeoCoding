package com.example.FindingNearestStore.controller;

import com.example.FindingNearestStore.api.CompanyAPI;
import com.example.FindingNearestStore.dto.CompanyDTO;
import com.example.FindingNearestStore.dto.PayLoadRequestDTO;
import com.example.FindingNearestStore.dto.ResponseDTO;
import com.example.FindingNearestStore.dto.TokenDTO;
import com.example.FindingNearestStore.model.Company;
import com.example.FindingNearestStore.repository.CompanyRepository;
import com.example.FindingNearestStore.service.CompanyService;
import com.example.FindingNearestStore.service.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class CompanyController implements CompanyAPI {
    @Autowired
    CompanyService companyService;
    @Autowired
    JWTService jwtService;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    CompanyRepository companyRepository;

    @Override
    public ResponseEntity<ResponseDTO> addCompany(CompanyDTO companyDTO) {
        Company company = companyService.addCompany(companyDTO);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(HttpStatus.OK, "Adding Company Details", company));

    }

    @Override
    public TokenDTO login(PayLoadRequestDTO payLoadRequestDTO) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(payLoadRequestDTO.getCompanyName(), payLoadRequestDTO.getPassword()));
        if (authentication.isAuthenticated()) {
            System.out.println("hjhiww");
            String token = jwtService.generateToken(payLoadRequestDTO);

            Optional<Company> company = companyRepository.findBycompanyName(payLoadRequestDTO.getCompanyName());
            String companyId = company.get().getCompanyId();
            String tenantId = company.get().getTenant();
            TokenDTO tokenDTO = new TokenDTO();
            tokenDTO.setToken(token);
            tokenDTO.setTenantId(tenantId);
            return tokenDTO;

        } else {
            throw new UsernameNotFoundException("invalid");
        }


    }
}
