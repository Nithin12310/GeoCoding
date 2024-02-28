package com.example.FindingNearestStore.controller;

import com.example.FindingNearestStore.api.SubscriptionAPI;
import com.example.FindingNearestStore.dto.*;
import com.example.FindingNearestStore.model.Subscription;
import com.example.FindingNearestStore.service.JWTService;
import com.example.FindingNearestStore.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SubscriptionController implements SubscriptionAPI {

    private final SubscriptionService subscriptionService;

    private final JWTService jwtService;
    @Override
    public ResponseEntity<ResponseDTO> subscribe(SubscriptionDTO subscriptionDTO) throws BadRequestException {
        Subscription subscription= subscriptionService.subscribe(subscriptionDTO);
        PayLoadRequestDTO payLoadRequestDTO= new PayLoadRequestDTO();
        payLoadRequestDTO.companyId= subscriptionDTO.getCompanyId();
        String token = jwtService.generateToken(payLoadRequestDTO);
        SubscriptionTokenDTO subscriptionTokenDTO= new SubscriptionTokenDTO();
        subscriptionTokenDTO.setToken(token);
        subscriptionTokenDTO.setSubscription(subscription);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(HttpStatus.OK,"Getting Subscription",subscriptionTokenDTO));
    }
}
