package com.example.studentsubscriptionservice.domain.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "students")
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 100, nullable = false, unique = true)
    private String name;

    private Student(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    private void validateInput(Long id, String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("잘못된 입력입니다.");
        }
    }

    public static Student createStudent(String name) {
        return new Student(null, name);
    }
}
