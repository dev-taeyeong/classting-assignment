package com.example.studentsubscriptionservice.framework.in.web;

import com.example.studentsubscriptionservice.application.in.dto.SchoolPageDto;
import com.example.studentsubscriptionservice.application.in.dto.StudentDto;
import com.example.studentsubscriptionservice.application.usecase.CreateStudentUseCase;
import com.example.studentsubscriptionservice.application.usecase.GetAllStudentsUseCase;
import com.example.studentsubscriptionservice.application.usecase.GetSubscriptionSchoolPagesByStudentIdUseCase;
import com.example.studentsubscriptionservice.application.usecase.IsSubscribedUseCase;
import com.example.studentsubscriptionservice.framework.in.web.request.CreateStudentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/v1/students")
@RestController
public class StudentApiController {

    private final CreateStudentUseCase createStudentUseCase;
    private final GetAllStudentsUseCase getAllStudentsUseCase;
    private final GetSubscriptionSchoolPagesByStudentIdUseCase getSubscriptionSchoolPagesByStudentIdUseCase;
    private final IsSubscribedUseCase isSubscribedUseCase;

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

    /**
     * 모든 학생 목록 조회
     *
     * @param pageable 페이징 조회를 위한 request
     * @return StudentDto Page 객체
     */
    @GetMapping
    public ResponseEntity<Page<StudentDto>> getAllStudents(
            @PageableDefault(page = 0, size = 20, sort = "id") Pageable pageable) {
        Page<StudentDto> studentDtos = getAllStudentsUseCase.getAllStudents(pageable);
        return ResponseEntity.ok(studentDtos);
    }

    /**
     * 학생이 구독중인 학교 페이지 목록 조회
     *
     * @param studentId 조회를 위한 학생 ID
     * @return 학생이 구독중인 학교 페이지 목록
     */
    @GetMapping("/{studentId}/subscriptions")
    public ResponseEntity<List<SchoolPageDto>> getAllSubscriptionSchoolPagesByStudentId(
            @PathVariable Long studentId
    ) {
        List<SchoolPageDto> subscriptionDtos =
                getSubscriptionSchoolPagesByStudentIdUseCase.getAllSubscriptionSchoolPagesByStudentId(studentId);
        return ResponseEntity.ok(subscriptionDtos);
    }

    /**
     * 학생의 학교 페이지 구독 여부 조회
     *
     * @param studentId 학생 ID
     * @param schoolPageId 학교 페이지 ID
     * @return 구독 여부
     */
    @GetMapping("/{studentId}/subscription-status")
    public ResponseEntity<Boolean> isSubscribed(
            @PathVariable Long studentId, @RequestParam Long schoolPageId
    ) {
        Boolean isSubscribed = isSubscribedUseCase.isSubscribed(studentId, schoolPageId);
        return ResponseEntity.ok(isSubscribed);
    }
}
