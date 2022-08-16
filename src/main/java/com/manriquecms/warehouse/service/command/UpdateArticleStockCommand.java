package com.manriquecms.warehouse.service.command;

import io.swagger.annotations.ApiModelProperty;

public class UpdateArticleStockCommand {
    @ApiModelProperty(hidden = true)
    private String articleId;
    private Integer stock;

    public UpdateArticleStockCommand(String articleId, Integer stock) {
        this.articleId = articleId;
        this.stock = stock;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId){
        this.articleId = articleId;
    }

    public Integer getStock() {
        return stock;
    }
}
