package com.manriquecms.warehouse.service.aggregator;

import com.manriquecms.warehouse.infrastructure.repository.ArticleRepository;
import com.manriquecms.warehouse.infrastructure.repository.ProductOrderRepository;
import com.manriquecms.warehouse.service.query.ArticleQuery;
import com.manriquecms.warehouse.service.query.ProductsAvailableQuery;
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

    /*public ProductOrder handleCreateOrderCommand(CreateOrderCommand createOrderCommand)
            throws OrderWithNotEnoughStockException {
        ProductOrder productOrder = new ProductOrder(
                createOrderCommand.getProductId(),
                createOrderCommand.getQuantity()
        );

        ProductBuildableDto productStock = productsAvailableQuery.getAvailableProduct(productOrder.getProductId());

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

    private Boolean haveEnoughStockForProduct(ProductBuildableDto productStock, ProductOrder productOrder) {
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
    }*/

}
