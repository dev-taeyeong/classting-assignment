package com.example.schoolpagemanagementservice.application.usecase;

import com.example.schoolpagemanagementservice.application.in.dto.CheckPermissionQuery;

public interface CheckPermissionUseCase {

    boolean checkPermission(CheckPermissionQuery query);
}
