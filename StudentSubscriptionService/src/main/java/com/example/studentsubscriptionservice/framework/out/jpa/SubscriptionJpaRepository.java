package com.example.studentsubscriptionservice.framework.out.jpa;

import com.example.studentsubscriptionservice.domain.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionJpaRepository extends JpaRepository<Subscription, Long> {
}
