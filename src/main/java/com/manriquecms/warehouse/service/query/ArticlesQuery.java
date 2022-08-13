package com.manriquecms.warehouse.service.query;

import com.manriquecms.warehouse.domain.model.article.Article;
import com.manriquecms.warehouse.infrastructure.repository.article.ArticleRepository;
import com.manriquecms.warehouse.service.exception.ArticleNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ArticlesQuery {
    @Autowired
    private ArticleRepository articleRepository;

    public ArticlesQuery(){}

    public List<Article> getAllArticles(){
        return StreamSupport.stream(articleRepository.findAll().spliterator(),false)
                .collect(Collectors.toList());
    }

    public Article getArticleById(String id) throws ArticleNotFoundException {
        return articleRepository.findById(id)
                .orElseThrow(() -> new ArticleNotFoundException(id));
    }
}
