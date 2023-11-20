package com.example.studentsubscriptionservice.domain.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(
        name = "subscriptions",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"school_page_id", "student_id"})
        }
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subscription that = (Subscription) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Subscription{" +
               "id=" + id +
               ", studentId=" + studentId +
               ", schoolPageId=" + schoolPageId +
               '}';
    }
}
