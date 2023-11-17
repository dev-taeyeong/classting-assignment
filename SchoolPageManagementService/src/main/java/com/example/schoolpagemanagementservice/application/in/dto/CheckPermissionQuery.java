package com.example.schoolpagemanagementservice.application.in.dto;

public record CheckPermissionQuery(

        Long schoolPageId,
        Long administratorId
) {
}
