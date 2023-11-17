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

    @GetMapping("/check")
    public ResponseEntity<Boolean> checkPermission(
            @RequestParam Long schoolPageId, @RequestParam Long administratorId) {
        boolean hasPermission = checkPermissionUseCase.checkPermission(new CheckPermissionQuery(schoolPageId, administratorId));
        return ResponseEntity.ok(hasPermission);
    }
}
