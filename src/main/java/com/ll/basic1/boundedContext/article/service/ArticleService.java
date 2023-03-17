package com.ll.basic1.boundedContext.article.service;

import com.ll.basic1.boundedContext.article.entity.Article;
import com.ll.basic1.boundedContext.article.entity.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ArticleService {
    private final ArticleRepository articleRepository;

    public Article findById(Long id) {
        return articleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. " + id));
    }

    @Transactional
    public Article save(Article article) {
        return articleRepository.save(article);
    }
}
