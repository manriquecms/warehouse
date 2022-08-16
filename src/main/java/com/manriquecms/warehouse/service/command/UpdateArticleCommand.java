package com.manriquecms.warehouse.service.command;

import io.swagger.annotations.ApiModelProperty;

public class UpdateArticleCommand {
    @ApiModelProperty(hidden = true)
    private String articleId;
    private String name;
    private Integer stock;

    public UpdateArticleCommand(String name, Integer stock) {
        this.name = name;
        this.stock = stock;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getName() {
        return name;
    }

    public Integer getStock() {
        return stock;
    }
}
