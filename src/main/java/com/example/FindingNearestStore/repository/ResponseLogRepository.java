package com.example.FindingNearestStore.repository;

import com.example.FindingNearestStore.model.ResponseLog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResponseLogRepository extends MongoRepository<ResponseLog,String> {
}
