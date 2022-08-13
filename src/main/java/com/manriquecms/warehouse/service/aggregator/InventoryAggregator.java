package com.manriquecms.warehouse.service.aggregator;

import com.manriquecms.warehouse.domain.model.article.Article;
import com.manriquecms.warehouse.infrastructure.repository.article.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryAggregator {
    @Autowired
    private ArticleRepository articleRepository;

    public InventoryAggregator(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }


    public List<Article> createArticles (List<Article> articles) {
        articles.stream().forEach(article -> createArticle(article));
        return articles;
    }

    public Article createArticle (Article article) {
        articleRepository.save(article);
        return article;
    }

}
