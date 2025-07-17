package com.my_medi.domain.healthCheckup.repository;

import com.my_medi.domain.healthCheckup.entity.HealthCheckupDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HealthCheckupRepository extends MongoRepository<HealthCheckupDocument, String> {
}
