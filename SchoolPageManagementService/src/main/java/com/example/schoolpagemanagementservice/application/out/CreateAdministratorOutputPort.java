package com.example.schoolpagemanagementservice.application.out;

import com.example.schoolpagemanagementservice.domain.model.Administrator;

public interface CreateAdministratorOutputPort {

    Administrator save(Administrator administrator);
}
