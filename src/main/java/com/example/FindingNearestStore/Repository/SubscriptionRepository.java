package com.example.FindingNearestStore.Repository;

import com.example.FindingNearestStore.Model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription,String> {



//
   Optional< Subscription> findBycompanyIdAndExpiryDateGreaterThan(String companyId, Date date);

//    Subscription findBycompanyId(String companyId);
//
//    Subscription findBycompanyIdAndexpiryDateGreaterThan(String comapnyId, Date date);
}


//CREATE TABLE subscriptions (
//        subscription_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
//plan_id UUID,
//company_id UUID,
//created_date TIMESTAMP,
//expiry_date TIMESTAMP
//
//);

