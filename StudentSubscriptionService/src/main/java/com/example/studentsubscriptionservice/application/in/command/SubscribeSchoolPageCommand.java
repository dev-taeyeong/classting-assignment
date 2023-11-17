package com.example.studentsubscriptionservice.application.in.command;

public record SubscribeSchoolPageCommand(

        Long studentId,
        Long schoolPageId
) {
}
