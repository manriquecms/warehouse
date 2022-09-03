package com.manriquecms.warehouse.service.query;

import com.manriquecms.warehouse.domain.dto.ProductBuildableDto;
import com.manriquecms.warehouse.domain.model.Product;
import com.manriquecms.warehouse.infrastructure.repository.ProductRepository;
import com.manriquecms.warehouse.service.exception.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ProductsAvailableQuery {
    @Autowired
    ProductRepository productRepository;

    @Transactional
    public List<ProductBuildableDto> getAvailableProducts() {
        return StreamSupport.stream(productRepository.findAll().spliterator(),false)
                .map(p -> {
                    return new ProductBuildableDto(
                            p.getName(),
                            howManyItemsCanIBuildOfProduct(p)
                    );
                }).collect(Collectors.toList());
    }

    public ProductBuildableDto getAvailableProduct(String productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));
        return new ProductBuildableDto(
                product.getName(),
                howManyItemsCanIBuildOfProduct(product)
        );
    }

    private Integer howManyItemsCanIBuildOfProduct(Product product){
        return product.getArticles().stream().map(productArticle ->
                    Double.valueOf(
                            Math.floor(productArticle.getArticle().getStock() / productArticle.getAmount())
                    ).intValue()
                ).min(Integer::compare).get();
    }

}
