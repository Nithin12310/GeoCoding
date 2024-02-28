package com.example.FindingNearestStore.dto;

import com.example.FindingNearestStore.model.Subscription;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubscriptionTokenDTO {

    private String Token;
    private Subscription subscription;
}
