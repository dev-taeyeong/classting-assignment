package com.example.schoolpagemanagementservice.application.out;

import com.example.schoolpagemanagementservice.domain.model.Administrator;

import java.util.List;

public interface AdministratorOutputPort {

    List<Administrator> fetchAll();
}
