package com.example.studentsubscriptionservice.application.in;

import com.example.studentsubscriptionservice.application.in.command.UnsubscribeSchoolPageCommand;
import com.example.studentsubscriptionservice.application.out.UnsubscribeSchoolPageOutputPort;
import com.example.studentsubscriptionservice.application.usecase.UnsubscribeSchoolPageUseCase;
import com.example.studentsubscriptionservice.domain.model.Subscription;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@RequiredArgsConstructor
@Transactional
@Service
public class UnsubscribeSchoolPageInputPort implements UnsubscribeSchoolPageUseCase {

    private final UnsubscribeSchoolPageOutputPort unsubscribeSchoolPageOutputPort;

    @Override
    public void unsubscribeSchoolPage(UnsubscribeSchoolPageCommand command) {
        Subscription subscription = unsubscribeSchoolPageOutputPort.fetchByStudentIdAndSchoolPageId(command.studentId(), command.schoolPageId())
                .orElseThrow(() -> new EntityNotFoundException("찾을 수 없는 엔티티입니다."));
        unsubscribeSchoolPageOutputPort.delete(subscription);
    }
}
