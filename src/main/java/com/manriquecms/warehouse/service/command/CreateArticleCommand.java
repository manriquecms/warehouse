package com.manriquecms.warehouse.service.command;

public class CreateArticleCommand {
    private String art_id;
    private String name;
    private Integer stock;

    public CreateArticleCommand(String art_id, String name, Integer stock) {
        this.art_id = art_id;
        this.name = name;
        this.stock = stock;
    }

    public String getArt_id() {
        return art_id;
    }

    public String getName() {
        return name;
    }

    public Integer getStock() {
        return stock;
    }
}
