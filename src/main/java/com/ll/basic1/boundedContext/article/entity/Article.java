package com.ll.basic1.boundedContext.article.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime createDate;

    private LocalDateTime modifyDate;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String body;

    @Builder
    public Article(LocalDateTime createDate, LocalDateTime modifyDate, String title, String body) {
        this.createDate = createDate;
        this.modifyDate = modifyDate;
        this.title = title;
        this.body = body;
    }
}
