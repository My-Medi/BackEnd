package com.my_medi.domain.healthCheckup.service;

import com.my_medi.infra.healthCheckup.entity.HealthCheckupDocument;

import java.util.List;

public interface HealthCheckupService {

    String saveSampleData();

    List<HealthCheckupDocument> findAll();
}
