package com.example.FindingNearestStore.service.implementation;

import com.example.FindingNearestStore.dto.CompanyDTO;
import com.example.FindingNearestStore.model.Company;
import com.example.FindingNearestStore.repository.CompanyRepository;
import com.example.FindingNearestStore.service.CompanyService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyServiceImplementation implements CompanyService {

    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public Company addCompany(CompanyDTO companyDTO) {

//        Company company= Company.builder().companyName(companyDTO.getCompanyName()).build();
        Company company = modelMapper.map(companyDTO, Company.class);
        return companyRepository.save(company);


    }
}
