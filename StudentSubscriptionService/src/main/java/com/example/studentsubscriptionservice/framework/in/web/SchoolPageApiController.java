package com.example.studentsubscriptionservice.framework.in.web;

import com.example.studentsubscriptionservice.application.in.command.SubscribeSchoolPageCommand;
import com.example.studentsubscriptionservice.application.in.command.UnsubscribeSchoolPageCommand;
import com.example.studentsubscriptionservice.application.in.dto.SubscriptionDto;
import com.example.studentsubscriptionservice.application.usecase.SubscribeSchoolPageUseCase;
import com.example.studentsubscriptionservice.application.usecase.UnsubscribeSchoolPageUseCase;
import com.example.studentsubscriptionservice.framework.in.web.request.SubscribeSchoolPageRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/v1/school-pages")
@RestController
public class SchoolPageApiController {

    private final SubscribeSchoolPageUseCase subscribeSchoolPageUseCase;
    private final UnsubscribeSchoolPageUseCase unsubscribeSchoolPageUseCase;

    /**
     * 학교 페이지 구독
     *
     * @param schoolPageId 학교 페이지 ID
     * @param request
     * @return SubscriptionDto
     */
    @PostMapping("/{schoolPageId}/subscriptions")
    public ResponseEntity<SubscriptionDto> subscribeSchoolPage(
            @PathVariable Long schoolPageId, @RequestBody SubscribeSchoolPageRequest request
    ) {
        SubscribeSchoolPageCommand command = new SubscribeSchoolPageCommand(request.studentId(), schoolPageId);
        SubscriptionDto subscriptionDto = subscribeSchoolPageUseCase.subscribeSchoolPage(command);

        return ResponseEntity.status(HttpStatus.CREATED).body(subscriptionDto);
    }

    /**
     * 학교 페이지 구독 취소
     *
     * @param schoolPageId 학교 페이지 ID
     * @param studentId 학생 ID
     * @return void
     */
    @DeleteMapping("/{schoolPageId}/subscriptions")
    public ResponseEntity<Void> unsubscribeSchoolPage(
            @PathVariable Long schoolPageId, @RequestParam Long studentId
    ) {
        UnsubscribeSchoolPageCommand command = new UnsubscribeSchoolPageCommand(studentId, schoolPageId);
        unsubscribeSchoolPageUseCase.unsubscribeSchoolPage(command);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
