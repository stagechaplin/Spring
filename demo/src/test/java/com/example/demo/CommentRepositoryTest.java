package com.example.demo;


import com.example.demo.entity.Article;
import com.example.demo.entity.Comment;
import com.example.demo.repository.CommentRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@DataJpaTest
public class CommentRepositoryTest {
    @Autowired
    CommentRepository commentRepository;
    @Test
    @DisplayName("특정 게시글의 모든 댓글 조회")
    void findByArticleId(){
        {
        //case1 - 4번 게시글의 모든 댓글 조회

            //1. 입력 데이터 조회
            Long articleId = 4L;
            //2. 실제 데이터
            List<Comment> comments = commentRepository.findByArticleId(articleId);
            //3. 예상 데이터
            Article article = new Article(4L, "당신의 인생 영화는?", "댓글 고");
            Comment a = new Comment(1L, article, "Park", "굿 윌 헌팅");
            Comment b = new Comment(2L, article, "Kim", "아이 엠 샘");
            Comment c = new Comment(3L, article, "Choi", "쇼생크 탈출");
            List<Comment> expected = Arrays.asList(a, b, c);
            //4. 비교 및 검증
            assertEquals(expected.toString(), comments.toString());
        }
    //case2 - 1번 게시글의 모든 댓글 조회
        {
   // 1. 입력 데이터 조회
            Long articleId = 1L;
            //2. 실제 데이터
            List<Comment> comments = commentRepository.findByArticleId(articleId);
            //3. 예상 데이터
            Article article = new Article(4L, "가가가가", "1111");
            List<Comment> expected = Arrays.asList();
            //4. 비교 및 검증
            assertEquals(expected.toString(), comments.toString());
        }
    }
    @Test
    @DisplayName("특정 닉네임의 모든 댓글 조회")
    void findByNickname(){
            String nickname = "Park";
            List<Comment> comments = commentRepository.findByNickname(nickname);
            Comment a = new Comment(1L,new Article(4L,"당신의 인생 영화는?","댓글 고"),
                                        nickname, "굿 윌 헌팅");
            Comment b = new Comment(4L,new Article(5L,"당신의 소울 푸드는?","댓글 고고"),
                                        nickname, "치킨");
            Comment c = new Comment(7L,new Article(6L,"당신의 취미는?","댓글 고고고"),
                                        nickname, "조깅");
            List<Comment> expected = Arrays.asList(a,b,c);
            assertEquals(expected.toString(),comments.toString());
    }
}
