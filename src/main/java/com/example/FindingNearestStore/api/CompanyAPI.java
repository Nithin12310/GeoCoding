package com.example.FindingNearestStore.api;

import com.example.FindingNearestStore.dto.CompanyDTO;
import com.example.FindingNearestStore.dto.PayLoadRequestDTO;
import com.example.FindingNearestStore.dto.ResponseDTO;
import com.example.FindingNearestStore.dto.TokenDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value = "${companyAPI}")
public interface CompanyAPI {

    @PostMapping("${addCompany}")
    public ResponseEntity<ResponseDTO> addCompany(@Valid @RequestBody CompanyDTO companyDTO) ;

    @PostMapping("${companyLogin}")
    public TokenDTO login(@RequestBody PayLoadRequestDTO payLoadRequestDTO);


}
