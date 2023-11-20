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
    private final GetNewsPostsBySchoolPageIdUseCase getNewsPostsBySchoolPageIdUseCase;

    /**
     * 소식을 생성합니다.
     *
     * @param request 뉴스 포스트 생성을 위한 요청 정보를 담은 {@link CreateNewsPostRequest} 객체.
     * @return 생성된 뉴스 포스트의 정보를 담은 {@link NewsPostDto}. HTTP 상태 코드로 201 (Created)가 반환됩니다.
     */
    @PostMapping
    public ResponseEntity<NewsPostDto> createNewsPost(@RequestBody CreateNewsPostRequest request) {
        NewsPostDto newsPostDto = createNewsPostUseCase.createNewsPost(request.toCommand());
        return ResponseEntity.status(HttpStatus.CREATED).body(newsPostDto);
    }

    /**
     * 모든 소식을 페이지네이션 형식으로 조회합니다.
     *
     * @param pageable 페이지네이션 정보를 담은 {@link Pageable} 객체.
     * @return 조회된 소식 목록을 담은 {@link Page<NewsPostDto>}. HTTP 상태 코드는 200 (OK)가 반환됩니다.
     */
    @GetMapping
    public ResponseEntity<Page<NewsPostDto>> getAllNewsPosts(
            @PageableDefault(page = 0, size = 20, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<NewsPostDto> newsPostDtos = getAllNewsPostsUseCase.getAllNewsPost(pageable);
        return ResponseEntity.ok(newsPostDtos);
    }

    /**
     * 지정된 ID를 가진 소식을 삭제합니다.
     *
     * @param newsPostId 삭제할 뉴스 포스트의 ID.
     * @return 삭제된 뉴스 포스트의 ID. HTTP 상태 코드로 204 (No Content)가 반환된다.
     */
    @DeleteMapping("{newsPostId}")
    public ResponseEntity<String> deleteNewsPostById(
            @PathVariable String newsPostId
    ) {
        String deletedNewsPostId = deleteNewsPostUseCase.deleteNewsPost(new DeleteNewsPostCommand(newsPostId));
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(deletedNewsPostId);
    }

    /**
     * 지정된 ID 목록에 해당하는 소식들을 조회합니다.
     *
     * @param newsPostIds 조회할 소식 ID 목록.
     * @return 조회된 소식들의 정보를 담은 {@link List<NewsPostDto>}. HTTP 상태 코드는 200 (OK)가 반환됩니다.
     */
    @GetMapping("/by-ids")
    public ResponseEntity<List<NewsPostDto>> getNewsPostsByIds(@RequestParam List<String> newsPostIds) {
        List<NewsPostDto> newsPostDtos = getNewsPostsByIdsUseCase.getNewsPostsByIds(newsPostIds);
        return ResponseEntity.ok(newsPostDtos);
    }

    /**
     * 지정된 ID를 가진 소식을 업데이트합니다.
     *
     * @param newsPostId 업데이트할 소식 ID.
     * @param request 소식 업데이트를 위한 요청 정보를 담은 {@link UpdateNewsPostRequest} 객체.
     * @return 업데이트된 소식 정보를 담은 {@link NewsPostDto}. HTTP 상태 코드는 200 (OK)가 반환됩니다.
     */
    @PutMapping("/{newsPostId}")
    public ResponseEntity<NewsPostDto> updateNewsPost(@PathVariable String newsPostId, @RequestBody UpdateNewsPostRequest request) {
        NewsPostDto newsPostDto = updateNewsPostUseCase.updateNewsPost(new UpdateNewsPostCommand(
                newsPostId,
                request.administratorId(),
                request.newTitle(),
                request.newContent()));
        return ResponseEntity.ok(newsPostDto);
    }

    /**
     * 구독중인 학교 페이지의 소식 목록 조회
     *
     * @param schoolPageId
     * @param studentId
     * @return
     */
    @GetMapping("/by-school-page-id/{schoolPageId}")
    public ResponseEntity<List<NewsPostDto>> getNewsPostsBySchoolPageIdAndStudentId(
            @PathVariable Long schoolPageId, @RequestParam Long studentId
    ) {
        List<NewsPostDto> newsPostDtos = getNewsPostsBySchoolPageIdUseCase.getNewsPostsBySchoolPageId(schoolPageId, studentId);
        return ResponseEntity.ok(newsPostDtos);
    }
}
