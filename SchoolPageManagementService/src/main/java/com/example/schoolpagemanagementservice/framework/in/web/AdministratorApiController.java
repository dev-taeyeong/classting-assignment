package com.example.schoolpagemanagementservice.framework.in.web;

import com.example.schoolpagemanagementservice.application.in.dto.AdministratorDto;
import com.example.schoolpagemanagementservice.application.usecase.CreateAdministratorUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/v1/administrators")
@RestController
public class AdministratorApiController {

    private final CreateAdministratorUseCase createAdministratorUseCase;

    @PostMapping
    public ResponseEntity<AdministratorDto> createAdministrator(
            @RequestBody CreateAdministratorRequest request
    ) {
        AdministratorDto administratorDto = createAdministratorUseCase.createAdministrator(request.toCommand());
        return ResponseEntity.status(HttpStatus.CREATED).body(administratorDto);
    }
}
