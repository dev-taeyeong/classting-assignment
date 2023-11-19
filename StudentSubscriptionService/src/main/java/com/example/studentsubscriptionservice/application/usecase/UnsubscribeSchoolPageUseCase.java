package com.example.studentsubscriptionservice.application.usecase;

import com.example.studentsubscriptionservice.application.in.command.UnsubscribeSchoolPageCommand;

public interface UnsubscribeSchoolPageUseCase {

    void unsubscribeSchoolPage(UnsubscribeSchoolPageCommand command);
}
