package com.example.FindingNearestStore.controller;

import com.example.FindingNearestStore.api.PlanAPI;
import com.example.FindingNearestStore.dto.PlanDTO;
import com.example.FindingNearestStore.dto.ResponseDTO;
import com.example.FindingNearestStore.model.Plan;
import com.example.FindingNearestStore.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlanController implements PlanAPI {
    @Autowired
    PlanService planService;

    @Override
    public ResponseEntity<ResponseDTO> addPlan(PlanDTO planDTO) {
        Plan plan = planService.addPlan(planDTO);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(HttpStatus.OK, "Adding Plans", plan));
    }
}
