package com.ll.basic1.boundedContext.article.controller;

import com.ll.basic1.base.rsdata.RsData;
import com.ll.basic1.boundedContext.article.entity.Article;
import com.ll.basic1.boundedContext.article.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Controller
@RequestMapping("/article")
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping("/write")
    @ResponseBody
    public RsData writeArticle(@RequestParam String title, @RequestParam String body) {
        if (title == null || title.trim().equals("")) {
            return RsData.of("F-1", "title을 넣어주세요");
        }

        if (body == null || body.trim().equals("")) {
            return RsData.of("F-2", "body를 넣어주세요");
        }

        Article article = Article.builder()
                .title(title)
                .body(body)
                .build();

        Article savedArticle = articleService.save(article);
        return RsData.of("S-1", "게시글이 생성되었습니다.", savedArticle);
    }
}
