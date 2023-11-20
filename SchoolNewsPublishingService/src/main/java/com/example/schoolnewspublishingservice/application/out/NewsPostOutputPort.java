package com.example.schoolnewspublishingservice.application.out;

import com.example.schoolnewspublishingservice.domain.model.NewsPost;

import java.util.List;

public interface NewsPostOutputPort {

    List<NewsPost> fetchBySchoolPageId(Long schoolPageId);
}
