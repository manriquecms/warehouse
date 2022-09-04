package com.manriquecms.warehouse.service.query;

import com.manriquecms.warehouse.domain.dto.ProductAvailableDto;
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
    public List<ProductAvailableDto> getAvailableProducts() {
        return StreamSupport.stream(productRepository.findAll().spliterator(),false)
                .map(p -> ProductAvailableDto.builder()
                        .productName(p.getName())
                        .quantity(howManyItemsCanIBuildOfProduct(p))
                        .build()
                ).collect(Collectors.toList());
    }

    public ProductAvailableDto getAvailableProduct(String productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));
        return ProductAvailableDto.builder()
                .productName(product.getName())
                .quantity(howManyItemsCanIBuildOfProduct(product))
                .build();
    }

    private Integer howManyItemsCanIBuildOfProduct(Product product){
        return product.getArticles().stream().map(productArticle ->
                    Double.valueOf(
                            Math.floor(productArticle.getArticle().getStock() / productArticle.getAmount())
                    ).intValue()
                ).min(Integer::compare).get();
    }

}
