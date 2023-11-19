package com.example.schoolnewspublishingservice.framework.in.web;

import com.example.schoolnewspublishingservice.application.in.dto.DeleteNewsPostCommand;
import com.example.schoolnewspublishingservice.application.in.dto.NewsPostDto;
import com.example.schoolnewspublishingservice.application.in.dto.UpdateNewsPostCommand;
import com.example.schoolnewspublishingservice.application.usecase.*;
import com.example.schoolnewspublishingservice.framework.in.web.request.CreateNewsPostRequest;
import com.example.schoolnewspublishingservice.framework.in.web.request.UpdateNewsPostRequest;
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
    private final UpdateNewsPostUseCase updateNewsPostUseCase;
    private final DeleteNewsPostUseCase deleteNewsPostUseCase;

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

    @DeleteMapping("{newsPostId}")
    public ResponseEntity<String> deleteNewsPostById(
            @PathVariable String newsPostId
    ) {
        String deletedNewsPostId = deleteNewsPostUseCase.deleteNewsPost(new DeleteNewsPostCommand(newsPostId));
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(deletedNewsPostId);
    }

    @GetMapping("/by-ids")
    public ResponseEntity<List<NewsPostDto>> getNewsPostsByIds(@RequestParam List<String> newsPostIds) {
        List<NewsPostDto> newsPostDtos = getNewsPostsByIdsUseCase.getNewsPostsByIds(newsPostIds);
        return ResponseEntity.ok(newsPostDtos);
    }

    @PutMapping("/{newsPostId}")
    public ResponseEntity<NewsPostDto> updateNewsPost(@PathVariable String newsPostId, @RequestBody UpdateNewsPostRequest request) {
        NewsPostDto newsPostDto = updateNewsPostUseCase.updateNewsPost(new UpdateNewsPostCommand(
                newsPostId,
                request.administratorId(),
                request.newTitle(),
                request.newContent()));
        return ResponseEntity.ok(newsPostDto);
    }
}
