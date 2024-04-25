package com.example.test01.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity //클래스가 entity인것을 선언
public class Article {
    @Id // 엔티티의 대푯값을 지정
    @GeneratedValue // 엔티티에 자동으로 숫자가 매겨지게 자동 생성 기능 추가
    private Long id;
    @Column // dto에 선언한 필드 값과 DB테이블 컬럼을 연결 및 선언
    private String title;
    @Column
    private String content;

    public Article(Long id, String title ,String content) { //Article 생성자를 추가 한다
        this.id = id;
        this.title = title;
        this.content = content;
    }
    @Override
    public String toString() {
        return "ArticleForm{" +
                "id = " +id+
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

}
