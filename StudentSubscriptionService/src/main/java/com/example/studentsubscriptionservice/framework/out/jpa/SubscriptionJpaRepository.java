package com.example.studentsubscriptionservice.framework.out.jpa;

import com.example.studentsubscriptionservice.domain.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SubscriptionJpaRepository extends JpaRepository<Subscription, Long> {

    Optional<Subscription> findByStudentIdAndSchoolPageId(long studentId, long schoolPageId);

    List<Subscription> findByStudentId(long studentId);

    List<Subscription> findBySchoolPageId(long schoolPageId);
}
