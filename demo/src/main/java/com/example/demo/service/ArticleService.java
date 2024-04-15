package com.example.demo.service;

import com.example.demo.dto.ArticleForm;
import com.example.demo.entity.Article;
import com.example.demo.repository.ArticleRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    public List<Article> index() {
        return articleRepository.findAll();
    }

    public Article show(@PathVariable Long id) {
        return articleRepository.findById(id).orElse(null);

    }


    public Article create(ArticleForm dto) {
        Article article = dto.toEntity();
        if (article.getId() != null) {
            return null;
        }
        return articleRepository.save(article);
    }

    public Article update(Long id,ArticleForm dto) {
        Article article = dto.toEntity();
        log.info("id:{},article:{}",id, article.toString());

        Article target = articleRepository.findById(id).orElse(null);
        //3. 잘못된 요청 처리하기
        if (target == null || id != article.getId()) {
             return null;
        }

        target.patch(article);
        Article updated = articleRepository.save(target);
        return updated;
    }
    public Article delete(@PathVariable Long id) {
        //1. 대상 찾기
        Article target = articleRepository.findById(id).orElse(null);
        //2. 잘못된 요청 처리하기
        if (target == null){
            return null;
        }
        //3. 대상 삭제하기
        articleRepository.delete(target);
        return target;
    }
@Transactional
    public List<Article> createArticles(List<ArticleForm> dtos) {
        //1.  dto -> entity
//        List <Article> articleList = new ArrayList<>();
//        for(int i = 0;i<dtos.size(); i++){
//            ArticleForm dto =dtos.get(i);
//            Article entity = dto.toEntity();
//            articleList.add(entity);
//
//         }
        List<Article> articleList = dtos.stream()
        .map(dto -> dto.toEntity())
        .collect(Collectors.toList());
        //2. DB 저장
        articleList.stream().forEach(article -> articleRepository.save(article));
        //3. 에러 발생
        articleRepository.findById(-1L).orElseThrow(() -> new IllegalArgumentException("결제 실패!"));
        //4. 결과값 반환
        return articleList;
    }
}
