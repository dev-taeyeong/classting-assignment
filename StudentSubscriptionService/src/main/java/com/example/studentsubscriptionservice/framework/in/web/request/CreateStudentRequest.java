package com.example.studentsubscriptionservice.framework.in.web.request;

import com.example.studentsubscriptionservice.application.in.command.CreateStudentCommand;

public record CreateStudentRequest(

        String name
) {

    public CreateStudentCommand toCommand() {
        return new CreateStudentCommand(name);
    }
}
