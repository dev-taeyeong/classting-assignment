package com.example.studentsubscriptionservice.domain.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(
        name = "subscriptions",
        indexes = {@Index(columnList = "student_id")}
)
@Entity
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "student_id", nullable = false)
    private Long studentId;

    @Column(name = "school_page_id", nullable = false)
    private Long schoolPageId;

    private Subscription(Long id, Long studentId, Long schoolPageId) {
        this.id = id;
        this.studentId = studentId;
        this.schoolPageId = schoolPageId;
    }

    public static Subscription createSubscription(Long studentId, Long schoolPageId) {
        return new Subscription(null, studentId, schoolPageId);
    }
}
