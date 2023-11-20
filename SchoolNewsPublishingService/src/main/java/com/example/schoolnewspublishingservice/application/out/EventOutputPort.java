package com.example.schoolnewspublishingservice.application.out;

import com.example.schoolnewspublishingservice.domain.model.event.SchoolNewsPublished;

public interface EventOutputPort {

    void occurSchoolNewsPublishedMessage(SchoolNewsPublished schoolNewsPublished);
}
