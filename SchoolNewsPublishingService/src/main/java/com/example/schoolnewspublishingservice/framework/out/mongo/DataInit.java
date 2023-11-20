package com.example.schoolnewspublishingservice.framework.out.mongo;

import com.example.schoolnewspublishingservice.domain.model.NewsPost;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class DataInit implements CommandLineRunner {

    private final NewsPostMongoRepository newsPostMongoRepository;

    @Override
    public void run(String... args) {
        for (int i = 1; i <= 5; i++) {
            newsPostMongoRepository.save(NewsPost.createNewsPost("테스트 ID " + i, i, "제목" + i, "내용" + i));
        }
    }
}
