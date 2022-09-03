package com.manriquecms.warehouse.domain.dto;

import com.manriquecms.warehouse.domain.model.Product;
import com.manriquecms.warehouse.domain.model.ProductArticle;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class ProductDto {
    private String name;
    private String price;
    private List<ProductArticleDto> articles;

    public static ProductDto fromProduct(Product product){
        return ProductDto.builder()
                .name(product.getName())
                .price(product.getPrice())
                .articles(
                        product.getArticles().stream().map(productArticle ->
                            ProductArticleDto.fromProductArticle(productArticle)
                        ).collect(Collectors.toList())
                )
                .build();
    }
}
