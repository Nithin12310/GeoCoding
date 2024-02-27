package com.example.FindingNearestStore.Repository;

import com.example.FindingNearestStore.Model.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanRepository extends JpaRepository<Plan,String> {


}

//CREATE TABLE plans (
//        plan_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
//plan_type VARCHAR(255),
//plan_description TEXT,
//number_of_requests BIGINT,
//expiry_days INT
//);
