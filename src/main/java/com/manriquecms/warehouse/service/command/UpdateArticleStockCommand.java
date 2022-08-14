package com.manriquecms.warehouse.service.command;

public class UpdateArticleStockCommand {
    private String art_id;
    private Integer stock;

    public UpdateArticleStockCommand(String art_id, Integer stock) {
        this.art_id = art_id;
        this.stock = stock;
    }

    public String getArt_id() {
        return art_id;
    }

    public Integer getStock() {
        return stock;
    }
}
