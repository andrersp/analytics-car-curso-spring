package com.analytics.analytics.service;

import org.springframework.stereotype.Service;

import com.analytics.analytics.dto.CarPostDTO;

@Service
public interface PostAnalyticsService {
    void saveDataAnalytics(CarPostDTO carPostDTO);

}
