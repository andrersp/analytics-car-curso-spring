package com.analytics.analytics.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.analytics.analytics.entity.CarModelAnalyticsEntity;

@Repository
public interface CarModelAnalicitsRepository extends JpaRepository<CarModelAnalyticsEntity, Long> {
    Optional<CarModelAnalyticsEntity> findByModel(String model);
}
