package com.example.schoolnewspublishingservice.domain.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Document(collection = "news_posts")
public class NewsPost {

    @Id
    private String id;

    @Indexed
    private long schoolPageId;
    private String title;
    private String content;

    public NewsPost(String id, long schoolPageId, String title, String content) {
        this.id = id;
        this.schoolPageId = schoolPageId;
        this.title = title;
        this.content = content;
    }

    public static NewsPost createNewsPost(long schoolPageId, String title, String content) {
        return new NewsPost(null, schoolPageId, title, content);
    }
}
