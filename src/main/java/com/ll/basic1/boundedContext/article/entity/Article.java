package com.ll.basic1.boundedContext.article.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime lastModifiedDate;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String body;

    @Builder
    public Article(LocalDateTime createDate, LocalDateTime modifyDate, String title, String body) {
        this.createdDate = createDate;
        this.lastModifiedDate = modifyDate;
        this.title = title;
        this.body = body;
    }
}
