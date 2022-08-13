package com.manriquecms.warehouse.domain.model.article;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.manriquecms.warehouse.domain.model.article.exceptions.InitializingStockNegativeNotAllowed;
import com.manriquecms.warehouse.domain.model.article.exceptions.NotEnoughStockToReduce;
import com.manriquecms.warehouse.domain.model.article.exceptions.NotNumberFormatForStockQuantity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Article {
    @Id
    private String id;
    private String name;
    private Integer stock;

    public Article() {
    }
    public Article(String id) {
        this.id = id;
    }

    public Article(String id, String name, Integer stock) {
        this.id = id;
        this.name = name;
        this.stock = stock;
    }

    @JsonProperty("art_id")
    public String getId() {
        return id;
    }

    @JsonProperty("art_id")
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(String stock)
            throws InitializingStockNegativeNotAllowed, NotNumberFormatForStockQuantity {
        this.stock = assignStock(stock);
    }

    public static Integer assignStock(String quantity)
            throws InitializingStockNegativeNotAllowed, NotNumberFormatForStockQuantity {
        try {
            return assignStock(Integer.parseInt(quantity));
        } catch (NumberFormatException exception) {
            throw new NotNumberFormatForStockQuantity();
        }
    }

    public static Integer assignStock(Integer quantity) throws InitializingStockNegativeNotAllowed {
        if (quantity >= 0) {
            return quantity;
        } else {
            throw new InitializingStockNegativeNotAllowed();
        }
    }

    public void reduceStock(Integer units) throws NotEnoughStockToReduce {
        if (units > this.stock) {
            throw new NotEnoughStockToReduce(units, this.stock);
        } else {
            this.stock -= units;
        }
    }

    public void incrementStock(Integer units){
        this.stock += units;
    }
}
