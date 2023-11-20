package com.example.schoolnewspublishingservice.application.out;

import com.example.schoolnewspublishingservice.domain.model.NewsPost;

import java.util.List;

public interface GetNewsPostsBySchoolPageIdOutputPort {

    List<NewsPost> fetchBySchoolPageId(Long schoolPageId);
}
