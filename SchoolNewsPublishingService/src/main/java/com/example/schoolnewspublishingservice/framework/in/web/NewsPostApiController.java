package com.example.schoolnewspublishingservice.framework.in.web;

import com.example.schoolnewspublishingservice.application.in.dto.NewsPostDto;
import com.example.schoolnewspublishingservice.application.usecase.CreateNewsPostUseCase;
import com.example.schoolnewspublishingservice.application.usecase.GetAllNewsPostsUseCase;
import com.example.schoolnewspublishingservice.application.usecase.GetNewsPostsByIdsUseCase;
import com.example.schoolnewspublishingservice.framework.in.web.request.CreateNewsPostRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/v1/news-posts")
@RestController
public class NewsPostApiController {

    private final CreateNewsPostUseCase createNewsPostUseCase;
    private final GetAllNewsPostsUseCase getAllNewsPostsUseCase;
    private final GetNewsPostsByIdsUseCase getNewsPostsByIdsUseCase;

    @PostMapping
    public ResponseEntity<NewsPostDto> createNewsPost(@RequestBody CreateNewsPostRequest request) {
        NewsPostDto newsPostDto = createNewsPostUseCase.createNewsPost(request.toCommand());
        return ResponseEntity.status(HttpStatus.CREATED).body(newsPostDto);
    }

    @GetMapping
    public ResponseEntity<Page<NewsPostDto>> getAllNewsPosts(
            @PageableDefault(page = 0, size = 20, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<NewsPostDto> newsPostDtos = getAllNewsPostsUseCase.getAllNewsPost(pageable);
        return ResponseEntity.ok(newsPostDtos);
    }

    @GetMapping("/by-ids")
    public ResponseEntity<List<NewsPostDto>> getNewsPostsByIds(@RequestParam List<String> newsPostIds) {
        List<NewsPostDto> newsPostDtos = getNewsPostsByIdsUseCase.getNewsPostsByIds(newsPostIds);
        return ResponseEntity.ok(newsPostDtos);
    }
}
