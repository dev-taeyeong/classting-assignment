package com.example.studentsubscriptionservice.application.in;

import com.example.studentsubscriptionservice.application.in.query.GetSubscribersQuery;
import com.example.studentsubscriptionservice.application.out.GetSubscribersOutputPort;
import com.example.studentsubscriptionservice.application.usecase.GetSubscribersUseCase;
import com.example.studentsubscriptionservice.domain.model.Subscription;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class GetSubscribersInputPort implements GetSubscribersUseCase {

    private final GetSubscribersOutputPort getSubscribersOutputPort;

    @Override
    public List<Long> getSubscribers(GetSubscribersQuery query) {
        List<Subscription> subscriptions = getSubscribersOutputPort.getSubscribersBySchoolPageId(query.schoolPageId());
        return subscriptions.stream()
                .map(Subscription::getStudentId)
                .toList();
    }
}
