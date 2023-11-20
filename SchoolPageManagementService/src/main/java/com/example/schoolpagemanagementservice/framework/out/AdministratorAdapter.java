package com.example.schoolpagemanagementservice.framework.out;

import com.example.schoolpagemanagementservice.application.out.AdministratorOutputPort;
import com.example.schoolpagemanagementservice.domain.model.Administrator;
import com.example.schoolpagemanagementservice.framework.out.jpa.AdministratorJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class AdministratorAdapter implements AdministratorOutputPort {

    private final AdministratorJpaRepository administratorJpaRepository;

    @Override
    public List<Administrator> fetchAll() {
        return administratorJpaRepository.findAll();
    }
}
