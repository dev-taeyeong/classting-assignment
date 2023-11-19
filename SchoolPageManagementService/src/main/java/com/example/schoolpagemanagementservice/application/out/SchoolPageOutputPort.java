package com.example.schoolpagemanagementservice.application.out;

public interface SchoolPageOutputPort {

    boolean existByIdAndAdministratorId(long schoolPageId, long administratorId);
}
