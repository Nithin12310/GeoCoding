package com.example.FindingNearestStore.Repository;

import com.example.FindingNearestStore.Model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company,String> {


    Optional<Company> findBycompanyName(String username);
}

//CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
//
//CREATE TABLE company (
//        company_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
//company_name VARCHAR(255),
//password VARCHAR(255),
//descryption TEXT,
//contact_number VARCHAR(20),
//email_id VARCHAR(255),
//gst_number VARCHAR(255),
//city VARCHAR(255),
//state VARCHAR(255),
//country VARCHAR(255),
//door VARCHAR(255),
//street VARCHAR(255),
//tenant VARCHAR(255)
//);