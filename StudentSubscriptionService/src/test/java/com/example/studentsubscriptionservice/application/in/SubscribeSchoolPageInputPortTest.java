package com.example.studentsubscriptionservice.application.in;

import com.example.studentsubscriptionservice.application.in.command.SubscribeSchoolPageCommand;
import com.example.studentsubscriptionservice.application.in.dto.SubscriptionDto;
import com.example.studentsubscriptionservice.application.out.SubscribeSchoolPageOutputPort;
import com.example.studentsubscriptionservice.domain.model.Subscription;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class SubscribeSchoolPageInputPortTest {

    @InjectMocks
    private SubscribeSchoolPageInputPort sut;

    @Mock
    private SubscribeSchoolPageOutputPort subscribeSchoolPageOutputPort;

    @Test
    void subscribeSchoolPageTest() {
        // given
        SubscribeSchoolPageCommand command = new SubscribeSchoolPageCommand(1L, 2L);
        Subscription subscription = Subscription.createSubscription(command.studentId(), command.schoolPageId());
        given(subscribeSchoolPageOutputPort.save(any(Subscription.class))).willReturn(subscription);

        // when
        SubscriptionDto result = sut.subscribeSchoolPage(command);

        // then
        assertThat(result.studentId()).isEqualTo(command.studentId());
        assertThat(result.schoolPageId()).isEqualTo(command.schoolPageId());

        then(subscribeSchoolPageOutputPort).should().save(any(Subscription.class));
    }
}
