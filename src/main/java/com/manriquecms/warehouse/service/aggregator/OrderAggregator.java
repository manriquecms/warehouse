package com.manriquecms.warehouse.service.aggregator;

import com.manriquecms.warehouse.domain.model.article.Article;
import com.manriquecms.warehouse.domain.model.article.exceptions.InitializingStockNegativeNotAllowedException;
import com.manriquecms.warehouse.domain.model.article.exceptions.NotEnoughStockToReduceException;
import com.manriquecms.warehouse.domain.model.product.ProductBuildable;
import com.manriquecms.warehouse.domain.model.product.ProductOrder;
import com.manriquecms.warehouse.domain.model.product.ProductPart;
import com.manriquecms.warehouse.infrastructure.repository.article.ArticleRepository;
import com.manriquecms.warehouse.infrastructure.repository.product.ProductOrderRepository;
import com.manriquecms.warehouse.service.command.CreateOrderCommand;
import com.manriquecms.warehouse.service.command.UpdateArticleStockCommand;
import com.manriquecms.warehouse.service.exception.OrderWithNotEnoughStockException;
import com.manriquecms.warehouse.service.query.ArticleQuery;
import com.manriquecms.warehouse.service.query.ProductsAvailableQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderAggregator {
    @Autowired
    ProductOrderRepository productOrderRepository;
    @Autowired
    ArticleRepository articleRepository;
    @Autowired
    ProductsAvailableQuery productsAvailableQuery;
    @Autowired
    ArticleQuery articleQuery;
    @Autowired
    ArticleAggregator articleAggregator;

    public ProductOrder handleCreateOrderCommand(CreateOrderCommand createOrderCommand)
            throws OrderWithNotEnoughStockException {
        ProductOrder productOrder = new ProductOrder(
                createOrderCommand.getProductId(),
                createOrderCommand.getQuantity()
        );

        ProductBuildable productStock = productsAvailableQuery.getAvailableProduct(productOrder.getProductId());

        if (haveEnoughStockForProduct(productStock, productOrder)) {
            productOrderRepository.save(productOrder);
            productStock.getProduct().getContain_articles().stream().forEach(productPart -> {
                reduceAndSaveArticleStock(productPart, productOrder.getQuantity());

            });
        } else {
            throw new OrderWithNotEnoughStockException();
        }

        return productOrder;
    }

    private Boolean haveEnoughStockForProduct(ProductBuildable productStock, ProductOrder productOrder) {
        return productStock.getQuantity() >= productOrder.getQuantity();
    }

    private Article reduceAndSaveArticleStock(ProductPart productPart, Integer quantityProducts)
            throws InitializingStockNegativeNotAllowedException {
        Article article = articleQuery.getArticleById(productPart.getArt_id());

        article.reduceStock(productPart.getAmount_of() * quantityProducts);

        UpdateArticleStockCommand updateArticleStockCommand =
                new UpdateArticleStockCommand(article.getId(), article.getStock());
        articleAggregator.handleUpdateArticleStockCommand(updateArticleStockCommand);

        return article;
    }

}
