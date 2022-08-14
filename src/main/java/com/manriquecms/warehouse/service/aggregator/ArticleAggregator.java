package com.manriquecms.warehouse.service.aggregator;

import com.manriquecms.warehouse.domain.model.article.Article;
import com.manriquecms.warehouse.domain.model.article.exceptions.InitializingStockNegativeNotAllowed;
import com.manriquecms.warehouse.domain.model.product.ProductOrder;
import com.manriquecms.warehouse.infrastructure.repository.article.ArticleRepository;
import com.manriquecms.warehouse.service.command.UpdateArticleCommand;
import com.manriquecms.warehouse.service.command.UpdateArticleStockCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleAggregator {
    @Autowired
    private ArticleRepository articleRepository;

    public ArticleAggregator(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public Article handleUpdateArticleStockCommand(UpdateArticleStockCommand updateArticleStockCommand)
            throws InitializingStockNegativeNotAllowed {
        Article article = articleRepository.findById(updateArticleStockCommand.getArt_id()).orElseThrow();
        article.assignStock(updateArticleStockCommand.getStock());

        articleRepository.save(article);

        return article;
    }

    public Article handleUpdateArticleCommand(UpdateArticleCommand updateArticleCommand) {
        Article article = new Article(
                updateArticleCommand.getArt_id(),
                updateArticleCommand.getName(),
                updateArticleCommand.getStock()
        );

        articleRepository.save(article);

        return article;
    }

    //TODO
    /*public List<Article> createArticles (List<Article> articles) {
        articles.stream().forEach(article -> createArticle(article));
        return articles;
    }

    public Article createArticle (Article article) {
        articleRepository.save(article);
        return article;
    }*/

}
