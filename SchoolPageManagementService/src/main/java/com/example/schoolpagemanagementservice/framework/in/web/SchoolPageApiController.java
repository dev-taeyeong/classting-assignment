package com.example.schoolpagemanagementservice.framework.in.web;

import com.example.schoolpagemanagementservice.application.in.dto.SchoolPageDto;
import com.example.schoolpagemanagementservice.application.usecase.CreateSchoolPageUseCase;
import com.example.schoolpagemanagementservice.application.usecase.GetAllSchoolPagesUseCase;
import com.example.schoolpagemanagementservice.framework.in.web.request.CreateSchoolPageRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/v1/school-pages")
@RestController
public class SchoolPageApiController {

    private final CreateSchoolPageUseCase createSchoolPageUseCase;
    private final GetAllSchoolPagesUseCase getAllSchoolPagesUseCase;

    @PostMapping
    public ResponseEntity<SchoolPageDto> createSchoolPage(@RequestBody CreateSchoolPageRequest request) {
        SchoolPageDto schoolPageDto = createSchoolPageUseCase.createSchoolPage(request.toCommand());
        return ResponseEntity.status(HttpStatus.CREATED).body(schoolPageDto);
    }

    @GetMapping
    public ResponseEntity<Page<SchoolPageDto>> getAllSchoolPages(
            @PageableDefault(page = 0, size = 20, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(getAllSchoolPagesUseCase.getSchoolPages(pageable));
    }
}
