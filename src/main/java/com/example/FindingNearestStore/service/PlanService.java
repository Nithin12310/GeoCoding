package com.example.FindingNearestStore.service;

import com.example.FindingNearestStore.dto.PlanDTO;
import com.example.FindingNearestStore.model.Plan;
import org.springframework.stereotype.Service;

@Service
public interface PlanService {

    public Plan addPlan(PlanDTO planDTO);
}
