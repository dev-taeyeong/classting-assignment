package com.example.studentsubscriptionservice.framework.in.web;

import com.example.studentsubscriptionservice.application.in.command.SubscribeSchoolPageCommand;
import com.example.studentsubscriptionservice.application.in.dto.StudentDto;
import com.example.studentsubscriptionservice.application.in.dto.SubscriptionDto;
import com.example.studentsubscriptionservice.application.usecase.CreateStudentUseCase;
import com.example.studentsubscriptionservice.application.usecase.GetAllStudentsUseCase;
import com.example.studentsubscriptionservice.application.usecase.SubscribeSchoolPageUseCase;
import com.example.studentsubscriptionservice.framework.in.web.request.CreateStudentRequest;
import com.example.studentsubscriptionservice.framework.in.web.request.SubscribeSchoolPageRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/v1/students")
@RestController
public class StudentApiController {

    private final CreateStudentUseCase createStudentUseCase;
    private final GetAllStudentsUseCase getAllStudentsUseCase;
    private final SubscribeSchoolPageUseCase subscribeSchoolPageUseCase;

    /**
     * 학생 생성
     *
     * @param request
     * @return StudentDto
     */
    @PostMapping
    public ResponseEntity<StudentDto> createStudent(@RequestBody CreateStudentRequest request) {
        StudentDto studentDto = createStudentUseCase.createStudent(request.toCommand());
        return ResponseEntity.status(HttpStatus.CREATED).body(studentDto);
    }

    @GetMapping
    public ResponseEntity<Page<StudentDto>> getAllStudents(
            @PageableDefault(page = 0, size = 20, sort = "id") Pageable pageable) {
        Page<StudentDto> studentDtos = getAllStudentsUseCase.getAllStudents(pageable);
        return ResponseEntity.ok(studentDtos);
    }

    /**
     * 학교 페이지 구독
     *
     * @param studentId 학생 ID
     * @param request
     * @return SubscriptionDto
     */
    @PostMapping("/{studentId}")
    public ResponseEntity<SubscriptionDto> subscribeSchoolPage(
            @PathVariable Long studentId, @RequestBody SubscribeSchoolPageRequest request) {
        SubscribeSchoolPageCommand command = new SubscribeSchoolPageCommand(studentId, request.schoolPageId());
        SubscriptionDto subscriptionDto = subscribeSchoolPageUseCase.subscribeSchoolPage(command);

        return ResponseEntity.status(HttpStatus.CREATED).body(subscriptionDto);
    }
}
