package com.manriquecms.warehouse.domain.dto;

import com.manriquecms.warehouse.domain.model.Article;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ArticleDto {
    private String name;
    private Integer stock;

    public static ArticleDto fromArticle(Article article){
        return ArticleDto.builder()
                .name(article.getName())
                .stock(article.getStock())
                .build();
    }
}
