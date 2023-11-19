package com.example.schoolnewspublishingservice.application.in;

import com.example.schoolnewspublishingservice.application.in.dto.DeleteNewsPostCommand;
import com.example.schoolnewspublishingservice.application.out.DeleteNewsPostOutputPort;
import com.example.schoolnewspublishingservice.application.usecase.DeleteNewsPostUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class DeleteNewsPostInputPort implements DeleteNewsPostUseCase {

    private final DeleteNewsPostOutputPort deleteNewsPostOutputPort;

    @Override
    public String deleteNewsPost(DeleteNewsPostCommand command) {
        deleteNewsPostOutputPort.removeById(command.newsPostId());
        return command.newsPostId();
    }
}
