package com.manriquecms.warehouse.service.aggregator;

import com.manriquecms.warehouse.domain.model.Article;
import com.manriquecms.warehouse.domain.model.exceptions.InitializingStockNegativeNotAllowedException;
import com.manriquecms.warehouse.infrastructure.repository.ArticleRepository;
import com.manriquecms.warehouse.service.command.CreateArticleCommand;
import com.manriquecms.warehouse.service.command.UpdateArticleCommand;
import com.manriquecms.warehouse.service.command.UpdateArticleStockCommand;
import com.manriquecms.warehouse.service.exception.ArticleAlreadyExistsException;
import com.manriquecms.warehouse.service.exception.ArticleNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(
        isolation = Isolation.READ_COMMITTED,
        propagation = Propagation.SUPPORTS,
        readOnly = false,
        timeout = 30)
public class ArticleAggregator {
    @Autowired
    private ArticleRepository articleRepository;

    public ArticleAggregator(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public Article handleUpdateArticleStockCommand(UpdateArticleStockCommand updateArticleStockCommand)
            throws InitializingStockNegativeNotAllowedException {
        Article article = articleRepository.findById(updateArticleStockCommand.getArticleId()).orElseThrow();
        article.assignStock(updateArticleStockCommand.getStock());

        articleRepository.save(article);

        return article;
    }

    public Article handleUpdateArticleCommand(UpdateArticleCommand updateArticleCommand) {
        Article article = new Article(
                updateArticleCommand.getArticleId(),
                updateArticleCommand.getName(),
                updateArticleCommand.getStock()
        );

        if (articleRepository.existsById(article.getId())) {
            articleRepository.save(article);
        } else {
            throw new ArticleNotFoundException(article.getId());
        }

        return article;
    }

    public Article handleCreateArticleCommand(CreateArticleCommand createArticleCommand) {
        Article article = new Article(
                createArticleCommand.getArt_id(),
                createArticleCommand.getName(),
                createArticleCommand.getStock()
        );
        if (!articleRepository.existsById(article.getId())) {
            articleRepository.save(article);
        } else {
            throw new ArticleAlreadyExistsException(article.getId());
        }

        return article;
    }

}
