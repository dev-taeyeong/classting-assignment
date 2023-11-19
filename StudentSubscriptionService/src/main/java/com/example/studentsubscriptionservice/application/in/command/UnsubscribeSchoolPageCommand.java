package com.example.studentsubscriptionservice.application.in.command;

public record UnsubscribeSchoolPageCommand(

        Long studentId,
        Long schoolPageId
) {
}
