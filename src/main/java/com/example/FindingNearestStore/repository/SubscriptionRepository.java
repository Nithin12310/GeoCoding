package com.example.FindingNearestStore.repository;

import com.example.FindingNearestStore.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription,String> {



//
    Optional<Subscription> findBycompanyIdAndExpiryDateGreaterThan(String companyId, Date date);

//    Subscription findBycompanyId(String companyId);
//
//    Subscription findBycompanyIdAndexpiryDateGreaterThan(String comapnyId, Date date);
}
