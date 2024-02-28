package com.example.FindingNearestStore.service;

import com.example.FindingNearestStore.dto.SubscriptionDTO;
import com.example.FindingNearestStore.model.Plan_subscription_company_view;
import com.example.FindingNearestStore.model.Subscription;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

@Service
public interface SubscriptionService {

    public Subscription subscribe(SubscriptionDTO subscriptionDTO) throws BadRequestException;

    public Long incrementRequestCountInRedis(String subscriptionId);

    public Plan_subscription_company_view getPlanDetailsByCompanyId(String comapnyId);

    public Boolean isSubscriptionValid(Plan_subscription_company_view planView);


}
