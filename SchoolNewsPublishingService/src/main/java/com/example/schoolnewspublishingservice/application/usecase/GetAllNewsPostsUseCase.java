package com.example.schoolnewspublishingservice.application.usecase;

import com.example.schoolnewspublishingservice.application.in.dto.NewsPostDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GetAllNewsPostsUseCase {

    Page<NewsPostDto> getAllNewsPost(Pageable pageable);
}
