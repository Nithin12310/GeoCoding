package com.example.FindingNearestStore.config;

import com.example.FindingNearestStore.model.Company;
import com.example.FindingNearestStore.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CompanyInfoDetailService implements UserDetailsService {

    @Autowired
    CompanyRepository companyRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Company> company= companyRepository.findBycompanyName(username);

       // System.out.println(username);
       return company.map(CompanyInfoUserDetailService::new).orElseThrow(()-> new UsernameNotFoundException("Company Not Found"));

    }
}
