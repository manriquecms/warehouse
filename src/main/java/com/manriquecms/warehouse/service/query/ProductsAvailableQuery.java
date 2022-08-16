package com.manriquecms.warehouse.service.query;

import com.manriquecms.warehouse.domain.model.article.Article;
import com.manriquecms.warehouse.domain.model.product.Product;
import com.manriquecms.warehouse.domain.model.product.ProductBuildable;
import com.manriquecms.warehouse.infrastructure.repository.article.ArticleRepository;
import com.manriquecms.warehouse.infrastructure.repository.article.ProductRepository;
import com.manriquecms.warehouse.service.exception.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jms.artemis.ArtemisNoOpBindingRegistry;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ProductsAvailableQuery {
    @Autowired
    ArticleRepository articleRepository;
    @Autowired
    ProductRepository productRepository;

    public List<ProductBuildable> getAvailableProducts() {
        return StreamSupport.stream(productRepository.findAll().spliterator(),false)
                .map(p -> {
                    return new ProductBuildable(
                            p,
                            howManyItemsCanIBuildOfProduct(p)
                    );
                }).collect(Collectors.toList());
    }

    public ProductBuildable getAvailableProduct(String productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));
        return new ProductBuildable(
                product,
                howManyItemsCanIBuildOfProduct(product)
        );
    }

    private Integer howManyItemsCanIBuildOfProduct(Product product){
        Map<String,Integer> articlesNeeded = product.mapForArticlesIdsAndAmountFromProduct();
        Map<String,Integer> articlesInStock = getArticlesStockToBuildProduct(product);

        return articlesNeeded.keySet().stream().map( articleId -> {
            return Double.valueOf(Math.floor(articlesInStock.get(articleId)/articlesNeeded.get(articleId))).intValue();
        }).min(Integer::compare).get();

    }

    private Map<String,Integer> getArticlesStockToBuildProduct(Product product){
        return StreamSupport.stream(articleRepository
                        .findAllById(product.mapForArticlesIdsAndAmountFromProduct().keySet()).spliterator(),false)
                .collect(Collectors.toMap(Article::getId,Article::getStock));
    }
}
