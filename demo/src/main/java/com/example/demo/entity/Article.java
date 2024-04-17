package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@AllArgsConstructor
@ToString
@NoArgsConstructor
@Getter
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "article_id")
    @SequenceGenerator(name = "article_id",sequenceName = "article_id", initialValue = 1,allocationSize = 1)
    private Long id;
    @Column
    private String title;
    @Column
    private String content;
 public void patch(Article article) {
    if(article.title != null)
        this.title = article.title;
    if(article.content != null)
        this.content = article.content;
 }
}
