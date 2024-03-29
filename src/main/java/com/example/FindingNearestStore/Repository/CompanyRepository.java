package com.example.FindingNearestStore.Repository;

import com.example.FindingNearestStore.Model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company,String> {


    Optional<Company> findBycompanyName(String username);
}
