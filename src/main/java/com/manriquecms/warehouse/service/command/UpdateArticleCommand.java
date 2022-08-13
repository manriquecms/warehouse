package com.manriquecms.warehouse.service.command;

public class UpdateArticleCommand {
    private String art_id;
    private String name;
    private String stock;

    public UpdateArticleCommand(String art_id, String name, String stock) {
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

    public String getStock() {
        return stock;
    }
}
