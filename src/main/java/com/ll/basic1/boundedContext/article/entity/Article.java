package com.ll.basic1.boundedContext.article.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

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
}
