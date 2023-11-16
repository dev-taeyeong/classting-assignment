package com.example.schoolnewspublishingservice.framework.out.mongo;

import com.example.schoolnewspublishingservice.domain.model.NewsPost;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NewsPostMongoRepository extends MongoRepository<NewsPost, String > {
}
