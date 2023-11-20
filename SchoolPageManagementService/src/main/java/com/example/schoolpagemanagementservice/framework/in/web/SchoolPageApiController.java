package com.example.schoolpagemanagementservice.framework.in.web;

import com.example.schoolpagemanagementservice.application.in.dto.SchoolPageDto;
import com.example.schoolpagemanagementservice.application.usecase.CreateSchoolPageUseCase;
import com.example.schoolpagemanagementservice.application.usecase.GetAllSchoolPagesUseCase;
import com.example.schoolpagemanagementservice.application.usecase.GetSchoolPagesByIdsUseCase;
import com.example.schoolpagemanagementservice.framework.in.web.request.CreateSchoolPageRequest;
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
@RequestMapping("/api/v1/school-pages")
@RestController
public class SchoolPageApiController {

    private final CreateSchoolPageUseCase createSchoolPageUseCase;
    private final GetAllSchoolPagesUseCase getAllSchoolPagesUseCase;
    private final GetSchoolPagesByIdsUseCase getSchoolPagesByIdsUseCase;

    /**
     * 학교 페이지 생성
     *
     * @param request 학교 페이지 생성 요청 정보를 담은 {@link CreateSchoolPageRequest} 객체.
     *                이 객체에는 학교 페이지를 생성하기 위해 필요한 정보가 포함되어 있습니다.
     * @return 생성된 학교 페이지의 정보를 담은 {@link SchoolPageDto}. HTTP 상태 코드로는 201 (Created)가 반환됩니다.
     */
    @PostMapping
    public ResponseEntity<SchoolPageDto> createSchoolPage(@RequestBody CreateSchoolPageRequest request) {
        SchoolPageDto schoolPageDto = createSchoolPageUseCase.createSchoolPage(request.toCommand());
        return ResponseEntity.status(HttpStatus.CREATED).body(schoolPageDto);
    }

    /**
     * 모든 학교 페이지 목록을 페이지네이션 형식으로 조회
     *
     * @param pageable 페이지네이션 정보를 담은 {@link Pageable} 객체.
     *                 기본값으로는 첫 번째 페이지(0)에 20개의 항목을 ID 내림차순으로 정렬하여 반환합니다.
     * @return 조회된 학교 페이지 목록을 담은 {@link Page<SchoolPageDto>}. HTTP 상태 코드로는 200 (OK)가 반환됩니다.
     */
    @GetMapping
    public ResponseEntity<Page<SchoolPageDto>> getAllSchoolPages(
            @PageableDefault(page = 0, size = 20, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(getAllSchoolPagesUseCase.getSchoolPages(pageable));
    }

    @GetMapping("/by-ids")
    public ResponseEntity<List<SchoolPageDto>> getSchoolPagesByIds(
            @RequestParam List<Long> schoolPageIds
    ) {
        List<SchoolPageDto> schoolPageDtos = getSchoolPagesByIdsUseCase.getSchoolPagesByIds(schoolPageIds);
        return ResponseEntity.ok(schoolPageDtos);
    }
}
