package com.example.schoolpagemanagementservice.framework.in.web;

import com.example.schoolpagemanagementservice.application.in.dto.AdministratorDto;
import com.example.schoolpagemanagementservice.application.usecase.CreateAdministratorUseCase;
import com.example.schoolpagemanagementservice.framework.in.web.request.CreateAdministratorRequest;
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

    /**
     * 새로운 관리자 계정을 생성합니다.
     *
     * @param request 관리자 생성을 위한 요청 정보를 담은 {@link CreateAdministratorRequest} 객체.
     *                이 객체는 새로운 관리자를 생성하기 위해 필요한 정보를 포함하고 있습니다.
     * @return 생성된 관리자의 정보를 담은 {@link AdministratorDto}. HTTP 상태 코드로는 201 (Created)가 반환됩니다.
     */
    @PostMapping
    public ResponseEntity<AdministratorDto> createAdministrator(
            @RequestBody CreateAdministratorRequest request
    ) {
        AdministratorDto administratorDto = createAdministratorUseCase.createAdministrator(request.toCommand());
        return ResponseEntity.status(HttpStatus.CREATED).body(administratorDto);
    }
}
