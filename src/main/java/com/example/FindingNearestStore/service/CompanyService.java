package com.example.FindingNearestStore.service;

import com.example.FindingNearestStore.dto.CompanyDTO;
import com.example.FindingNearestStore.model.Company;
import org.springframework.stereotype.Service;

@Service
public interface CompanyService {
    Company addCompany(CompanyDTO companyDTO);
}
