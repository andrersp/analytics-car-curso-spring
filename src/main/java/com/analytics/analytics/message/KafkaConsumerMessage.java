package com.analytics.analytics.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.analytics.analytics.dto.CarPostDTO;
import com.analytics.analytics.service.PostAnalyticsService;;

@Component
public class KafkaConsumerMessage {

    private final Logger LOG = LoggerFactory.getLogger(KafkaConsumerMessage.class);

    @Autowired
    private PostAnalyticsService postAnalyticsService;

    @KafkaListener(topics = "car-post-topic", groupId = "analytics-posts-group")
    public void listening(CarPostDTO carPost) {
        LOG.info("ANALYTICS DATA -> Received Car Post information: {}", carPost);
        postAnalyticsService.saveDataAnalytics(carPost);
    }

}
