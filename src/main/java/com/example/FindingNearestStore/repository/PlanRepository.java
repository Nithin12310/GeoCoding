package com.example.FindingNearestStore.repository;

import com.example.FindingNearestStore.model.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanRepository extends JpaRepository<Plan,String> {


}
