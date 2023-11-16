package com.example.schoolnewspublishingservice.framework.out.mongo;

import com.example.schoolnewspublishingservice.domain.model.NewsPost;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface NewsPostMongoRepository extends MongoRepository<NewsPost, String> {

    List<NewsPost> findByIdIn(List<String> newsPostIds);
}
