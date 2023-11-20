package com.example.studentsubscriptionservice.application.in;

import com.example.studentsubscriptionservice.application.in.dto.SchoolPageDto;
import com.example.studentsubscriptionservice.application.out.GetAllSubscriptionsByStudentIdOutputPort;
import com.example.studentsubscriptionservice.application.out.SchoolPageOutputPort;
import com.example.studentsubscriptionservice.application.usecase.GetSubscriptionSchoolPagesByStudentIdUseCase;
import com.example.studentsubscriptionservice.domain.model.Subscription;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class GetSubscriptionSchoolPagesByStudentIdInputPort implements GetSubscriptionSchoolPagesByStudentIdUseCase {

    private final GetAllSubscriptionsByStudentIdOutputPort getAllSubscriptionsByStudentIdOutputPort;
    private final SchoolPageOutputPort schoolPageOutputPort;

    @Override
    public List<SchoolPageDto> getAllSubscriptionSchoolPagesByStudentId(long studentId) {
        List<Long> schoolPageIds = getAllSubscriptionsByStudentIdOutputPort.fetchByStudentId(studentId).stream()
                .map(Subscription::getSchoolPageId)
                .toList();

        return schoolPageOutputPort.getSchoolPageDatasByIds(schoolPageIds);
    }
}
