package com.example.schoolnewspublishingservice.application.out;

public interface SchoolPagePermissionOutputPort {

    boolean checkPermission(long administratorId, long schoolPageId);
}
