package com.example.demo.controller;

import com.example.demo.dto.ArticleForm;
import com.example.demo.dto.CommentDto;
import com.example.demo.entity.Article;
import com.example.demo.repository.ArticleRepository;
import com.example.demo.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Slf4j
@Controller
public class ArticlerController {
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private CommentService commentService;
    @GetMapping("/articles/new")
    public String newArticlesForm(){
        return "articles/new";
    }
    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form){
//        System.out.println(form.toString());
        //1. DTO -> entity
        log.info(form.toString());
        Article article =form.toEntity();
        log.info(article.toString());
//        System.out.println(article.toString());

        //2. repository로 entity를 DB에 저장
        Article saved = articleRepository.save(article);
//        System.out.println(saved.toString());
        log.info(saved.toString());
        return "redirect:/articles/"+saved.getId();
    }
    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model){
        log.info("id="+id);
//        1.id 조회 해서 데이터 가져오기
         Article articleEntity = articleRepository.findById(id).orElse(null);
         List<CommentDto> commentsDtos = commentService.comments(id);
//         log.info("article :" +articleEntity.toString());
//        2. 모델에 데이터 등록하기
         model.addAttribute("article",articleEntity);
         model.addAttribute("commentDtos",commentsDtos);
//        3. view 페이지 반환하기
        return "articles/show";
    }
    @GetMapping("/articles")
    public String index(Model model){
//        1.모든 데이터 가져오기
          List<Article> articleEntityList =   articleRepository.findAll();
//        2. 모델 데이터 등록하기
          model.addAttribute("articleList",articleEntityList);
//        3. 뷰페이지 설정하기
        return "articles/index";

    }
    @GetMapping("/articles/{id}/edit")
        public String edit(@PathVariable Long id, Model model){
//            1.수정할 데이터 가져오기
                Article articleEntity = articleRepository.findById(id).orElse(null);
//            2.모델에 담기
                model.addAttribute("article",articleEntity);
//            3.뷰에 전달하기
                return "articles/edit";
    }
    @PostMapping("/articles/update")
        public String update(ArticleForm form){
            log.info(form.toString());
//            1. dto 를  엔티티로 변환
                Article articleEntity = form.toEntity();
                log.info(articleEntity.toString());
//            2.엔티티를 DB에 저장
//                2-1. DB에서 기존데이터 가져오기
                Article target = articleRepository.findById(articleEntity.getId()).orElse(null);
//                  2-2. 기존 데이터 값을 갱신
                if(target !=null){
                    articleRepository.save(articleEntity);
                }
//            3. 수정된 결과를 뷰에 보냄
            return "redirect:/articles/"+articleEntity.getId();
        }
        @GetMapping("/articles/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr){
            log.info("삭제요청이 들어왔습니다.");
//            1. 삭제 대상 가져오기
                Article target = articleRepository.findById(id).orElse(null);
                log.info(target.toString());
//            2. 대상 엔티티를 삭제
                if(target != null){
                    articleRepository.delete(target);
                rttr.addFlashAttribute("msg","삭제되었습니다.");
                }
//            3. 결과 뷰에 리다이렉트
                return "redirect:/articles";


        }
    }

