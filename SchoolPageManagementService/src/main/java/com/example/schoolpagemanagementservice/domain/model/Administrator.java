package com.example.schoolpagemanagementservice.domain.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "administrators")
@Entity
public class Administrator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    private Administrator(Long id, String name) {
        validateInput(id, name);
        this.id = id;
        this.name = name;
    }

    private void validateInput(Long id, String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("잘못된 이름입니다.");
        }
    }

    public static Administrator createAdministrator(String name) {
        return new Administrator(null, name);
    }
}
