package com.example.FindingNearestStore.service.implementation;

import com.example.FindingNearestStore.dto.PlanDTO;
import com.example.FindingNearestStore.model.Plan;
import com.example.FindingNearestStore.repository.PlanRepository;
import com.example.FindingNearestStore.service.PlanService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlanServiceImplementation implements PlanService {
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    PlanRepository planRepository;

    @Override
    public Plan addPlan(PlanDTO planDTO) {
        Plan plan = modelMapper.map(planDTO, Plan.class);
        return planRepository.save(plan);
    }
}
