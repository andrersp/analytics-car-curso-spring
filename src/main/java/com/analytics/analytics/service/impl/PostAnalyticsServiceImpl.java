package com.analytics.analytics.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.analytics.analytics.dto.CarPostDTO;
import com.analytics.analytics.entity.BrandAnalyticsEntity;
import com.analytics.analytics.entity.CarModelAnalyticsEntity;
import com.analytics.analytics.entity.CarModelPriceEntity;
import com.analytics.analytics.repository.BrandAnalyticsRepository;
import com.analytics.analytics.repository.CarModelAnalicitsRepository;
import com.analytics.analytics.repository.CarPriceAnalyticsRepository;
import com.analytics.analytics.service.PostAnalyticsService;

@Service
public class PostAnalyticsServiceImpl implements PostAnalyticsService {

    @Autowired
    private CarModelAnalicitsRepository carModelAnalicitsRepository;

    @Autowired
    private BrandAnalyticsRepository brandAnalyticsRepository;

    @Autowired
    private CarPriceAnalyticsRepository carPriceAnalyticsRepository;

    @Override
    public void saveDataAnalytics(CarPostDTO carPostDTO) {

        saveBrandAnalytics(carPostDTO.getBrand());
        saveCarModelAnalytics(carPostDTO.getModel());
        saveCarModelPriceAnalytics(carPostDTO.getModel(), carPostDTO.getPrice());

    }

    private void saveBrandAnalytics(String brand) {

        BrandAnalyticsEntity brandAnalyticsEntity = new BrandAnalyticsEntity();

        brandAnalyticsRepository.findByBrand(brand).ifPresentOrElse(item -> {
            item.setPosts(item.getPosts() + 1);
            brandAnalyticsRepository.save(item);
        }, () -> {
            brandAnalyticsEntity.setBrand(brand);
            brandAnalyticsEntity.setPosts(1L);
            brandAnalyticsRepository.save(brandAnalyticsEntity);
        });

    }

    private void saveCarModelAnalytics(String model) {
        CarModelAnalyticsEntity carModelAnalyticsEntity = new CarModelAnalyticsEntity();

        carModelAnalicitsRepository.findByModel(model).ifPresentOrElse(item -> {
            item.setPosts(item.getPosts() + 1);
            carModelAnalicitsRepository.save(item);
        }, () -> {
            carModelAnalyticsEntity.setModel(model);
            carModelAnalyticsEntity.setPosts(1L);
            carModelAnalicitsRepository.save(carModelAnalyticsEntity);
        });

    }

    private void saveCarModelPriceAnalytics(String model, Double price) {
        CarModelPriceEntity carModelPriceEntity = new CarModelPriceEntity();

        carModelPriceEntity.setModel(model);
        carModelPriceEntity.setPrice(price);

        carPriceAnalyticsRepository.save(carModelPriceEntity);

    }

}
