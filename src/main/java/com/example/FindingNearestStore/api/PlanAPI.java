package com.example.FindingNearestStore.api;

import com.example.FindingNearestStore.dto.PlanDTO;
import com.example.FindingNearestStore.dto.ResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value = "${planAPI}")
public interface PlanAPI {
    @PostMapping(value = "${addPlan}")
    public ResponseEntity<ResponseDTO> addPlan(@RequestBody PlanDTO planDTO);
}
