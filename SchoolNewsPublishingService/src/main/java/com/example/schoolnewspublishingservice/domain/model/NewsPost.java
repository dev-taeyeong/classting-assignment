package com.example.schoolnewspublishingservice.domain.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

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

    private NewsPost(String id, long schoolPageId, String title, String content) {
        validateInput(title, content);
        this.id = id;
        this.schoolPageId = schoolPageId;
        this.title = title;
        this.content = content;
    }

    public static NewsPost createNewsPost(long schoolPageId, String title, String content) {
        return new NewsPost(null, schoolPageId, title, content);
    }

    public void update(String newTitle, String newContent) {
        validateInput(newTitle, newContent);
        title = newTitle;
        content = newContent;
    }

    private void validateInput(String title, String content) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("잘못된 입력입니다.");
        }
        if (content == null || content.isBlank()) {
            throw new IllegalArgumentException("잘못된 입력입니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewsPost newsPost = (NewsPost) o;
        return Objects.equals(id, newsPost.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "NewsPost{" +
               "id='" + id + '\'' +
               ", schoolPageId=" + schoolPageId +
               ", newTitle='" + title + '\'' +
               ", newContent='" + content + '\'' +
               '}';
    }
}
