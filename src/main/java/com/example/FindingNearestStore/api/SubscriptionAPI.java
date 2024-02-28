package com.example.FindingNearestStore.api;

import com.example.FindingNearestStore.dto.ResponseDTO;
import com.example.FindingNearestStore.dto.SubscriptionDTO;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value = "${subscriptionAPI}")
public interface SubscriptionAPI {
    @PostMapping("${addSubscription}")
    public ResponseEntity<ResponseDTO> subscribe(@RequestBody SubscriptionDTO subscriptionDTO) throws BadRequestException;
}
