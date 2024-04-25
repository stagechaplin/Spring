package com.example.test01.Controller;

import com.example.test01.entity.Article;
import com.example.test01.dto.ArticleForm;
import com.example.test01.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller

public class ArticleController {
    @Autowired // 미리 생성해 놓은 repository 객체 주입
    private ArticleRepository articleRepository; //articleRepository 선언
    @GetMapping("/articles/new")
    public String newArticlesForm() {
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form){
        System.out.println(form.toString());

        Article article = form.toEntity();
        System.out.println(article.toString());

        Article saved = articleRepository.save(article); // article 엔티티를 저장해 save 객체에 반환
        System.out.println(saved.toString());
        return "";
    }

}
