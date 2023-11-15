package com.example.schoolpagemanagementservice.domain.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "school_pages")
@Entity
public class SchoolPage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "location", length = 100, nullable = false)
    private String location;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    private SchoolPage(Long id, String location, String name) {
        validateInput(id, location, name);
        this.id = id;
        this.location = location;
        this.name = name;
    }

    private void validateInput(Long id, String location, String name) {
        if (location == null || location.isBlank()) {
            throw new IllegalArgumentException("잘못된 지역입니다.");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("잘못된 일름입니다.");
        }
    }

    public static SchoolPage createSchoolPage(String location, String name) {
        return new SchoolPage(null, location, name);
    }
}
