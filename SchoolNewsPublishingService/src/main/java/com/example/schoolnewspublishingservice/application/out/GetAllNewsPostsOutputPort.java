package com.example.schoolnewspublishingservice.application.out;

import com.example.schoolnewspublishingservice.domain.model.NewsPost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GetAllNewsPostsOutputPort {

    Page<NewsPost> fetchAll(Pageable pageable);
}
