package com.example.schoolnewspublishingservice.framework.out;

import com.example.schoolnewspublishingservice.application.out.EventOutputPort;
import com.example.schoolnewspublishingservice.domain.model.event.SchoolNewsPublished;
import com.example.schoolnewspublishingservice.framework.out.kafka.NewsKafkaProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class EventAdapter implements EventOutputPort {

    private final NewsKafkaProducer newsKafkaProducer;

    @Override
    public void occurSchoolNewsPublishedMessage(SchoolNewsPublished schoolNewsPublished) {
        newsKafkaProducer.sendSchoolNewsPublishedMessage(schoolNewsPublished);
    }
}
