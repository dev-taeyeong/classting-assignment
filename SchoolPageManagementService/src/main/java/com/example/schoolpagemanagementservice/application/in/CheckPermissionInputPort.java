package com.example.schoolpagemanagementservice.application.in;

import com.example.schoolpagemanagementservice.application.in.dto.CheckPermissionQuery;
import com.example.schoolpagemanagementservice.application.out.SchoolPageOutputPort;
import com.example.schoolpagemanagementservice.application.usecase.CheckPermissionUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class CheckPermissionInputPort implements CheckPermissionUseCase {

    private final SchoolPageOutputPort schoolPageOutputPort;

    @Override
    public boolean checkPermission(CheckPermissionQuery query) {
        return schoolPageOutputPort.existByIdAndAdministratorId(query.schoolPageId(), query.administratorId());
    }
}
