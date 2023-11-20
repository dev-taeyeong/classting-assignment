package com.example.schoolnewspublishingservice.framework.out.kafka;

import com.example.schoolnewspublishingservice.domain.model.event.SchoolNewsPublished;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

@RequiredArgsConstructor
@Component
public class NewsKafkaProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendSchoolNewsPublishedMessage(SchoolNewsPublished schoolNewsPublished) {
        ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send("SchoolNewsPublished-topic", schoolNewsPublished);

        future.addCallback(result -> {}, ex -> {});
    }
}
