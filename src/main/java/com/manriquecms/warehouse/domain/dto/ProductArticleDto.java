package com.manriquecms.warehouse.domain.dto;

import com.manriquecms.warehouse.domain.model.ProductArticle;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductArticleDto {
    private String name;
    private Integer amount;

    public static ProductArticleDto fromProductArticle(ProductArticle productArticle){
        return ProductArticleDto.builder()
                .name(productArticle.getArticle().getName())
                .amount(productArticle.getAmount())
                .build();
    }
}
