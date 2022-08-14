package com.manriquecms.warehouse.service.aggregator;

import com.manriquecms.warehouse.domain.model.article.Article;
import com.manriquecms.warehouse.domain.model.article.exceptions.NotEnoughStockToReduce;
import com.manriquecms.warehouse.domain.model.product.Product;
import com.manriquecms.warehouse.domain.model.product.ProductBuildable;
import com.manriquecms.warehouse.domain.model.product.ProductOrder;
import com.manriquecms.warehouse.infrastructure.repository.article.ArticleRepository;
import com.manriquecms.warehouse.infrastructure.repository.product.ProductOrderRepository;
import com.manriquecms.warehouse.service.command.CreateOrderCommand;
import com.manriquecms.warehouse.service.query.ArticlesQuery;
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
    ArticlesQuery articlesQuery;

    public Product handleCreateOrderCommand(CreateOrderCommand createOrderCommand) {
        ProductOrder productOrder = new ProductOrder(
                createOrderCommand.getProductId(),
                createOrderCommand.getQuantity()
        );

        ProductBuildable productStock = productsAvailableQuery.getAvailableProduct(productOrder.getProductId());


        if (productStock.getQuantity() >= productOrder.getQuantity()) {
            productOrderRepository.save(productOrder);

            productStock.getProduct().getContain_articles().stream().forEach(productPart -> {
                Article article = articlesQuery.getArticleById(productPart.getArt_id());
                try {
                    article.reduceStock(productPart.getAmount_of());
                } catch (NotEnoughStockToReduce e) {
                    throw new RuntimeException(e);
                }
                articleRepository.save(article);
            });
        }

        return productStock.getProduct();
    }

    private Boolean haveEnoughStockForProduct(ProductOrder productOrder) {
        ProductBuildable productStock = productsAvailableQuery.getAvailableProduct(productOrder.getProductId());
        return productStock.getQuantity() >= productOrder.getQuantity();
    }


}
