package com.example.schoolpagemanagementservice.framework.in.web;

import com.example.schoolpagemanagementservice.application.in.CreateSchoolPageCommand;
import com.example.schoolpagemanagementservice.application.in.SchoolPageDto;
import com.example.schoolpagemanagementservice.application.usecase.CreateSchoolPageUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController("/api/v1/school-page")
public class SchoolPageApiController {

    private final CreateSchoolPageUseCase createSchoolPageUseCase;

    @PostMapping
    public ResponseEntity<SchoolPageDto> createSchoolPage(@RequestBody CreateSchoolPageRequest request) {
        SchoolPageDto schoolPageDto = createSchoolPageUseCase.createSchoolPage(request.toCommand());
        return ResponseEntity.status(HttpStatus.CREATED).body(schoolPageDto);
    }
}
