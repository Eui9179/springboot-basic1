package com.ll.basic1.boundedContext.article.entity;

import jakarta.persistence.*;

@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String body;
}
