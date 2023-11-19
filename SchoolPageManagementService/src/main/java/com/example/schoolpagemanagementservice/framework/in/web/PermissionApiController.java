package com.example.schoolpagemanagementservice.framework.in.web;

import com.example.schoolpagemanagementservice.application.in.dto.CheckPermissionQuery;
import com.example.schoolpagemanagementservice.application.usecase.CheckPermissionUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/v1/permissions")
@RestController
public class PermissionApiController {

    private final CheckPermissionUseCase checkPermissionUseCase;

    /**
     * 주어진 관리자가 특정 학교 페이지에 대한 접근 권한을 가지고 있는지 확인합니다.
     *
     * @param schoolPageId 접근 권한을 확인할 학교 페이지의 ID.
     * @param administratorId 권한 확인을 요청하는 관리자의 ID.
     * @return 관리자가 해당 학교 페이지에 대한 접근 권한이 잇으면 true, 없으면 false를 반환합니다.
     */
    @GetMapping("/check")
    public ResponseEntity<Boolean> checkPermission(
            @RequestParam Long schoolPageId, @RequestParam Long administratorId) {
        boolean hasPermission = checkPermissionUseCase.checkPermission(new CheckPermissionQuery(schoolPageId, administratorId));
        return ResponseEntity.ok(hasPermission);
    }
}
