package com.example.schoolnewspublishingservice.application.out;

import com.example.schoolnewspublishingservice.domain.model.NewsPost;

import java.util.List;

public interface GetNewsPostsByIdsOutputPort {

    List<NewsPost> fetchNewsPostsByIds(List<String> newsPostIds);
}
