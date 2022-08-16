package com.manriquecms.warehouse.domain.model.article;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.manriquecms.warehouse.domain.model.article.exceptions.InitializingStockNegativeNotAllowedException;
import com.manriquecms.warehouse.domain.model.article.exceptions.NotEnoughStockToReduceException;
import com.manriquecms.warehouse.domain.model.article.exceptions.NotNumberFormatForStockQuantityException;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

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
            throws InitializingStockNegativeNotAllowedException, NotNumberFormatForStockQuantityException {
        this.stock = assignStock(stock);
    }

    public Integer assignStock(String quantity)
            throws InitializingStockNegativeNotAllowedException, NotNumberFormatForStockQuantityException {
        try {
            return assignStock(Integer.parseInt(quantity));
        } catch (NumberFormatException exception) {
            throw new NotNumberFormatForStockQuantityException();
        }
    }

    public Integer assignStock(Integer quantity) throws InitializingStockNegativeNotAllowedException {
        if (quantity >= 0) {
            return quantity;
        } else {
            throw new InitializingStockNegativeNotAllowedException();
        }
    }

    public void reduceStock(Integer units) throws NotEnoughStockToReduceException {
        if (units > this.stock) {
            throw new NotEnoughStockToReduceException(units, this.stock);
        } else {
            this.stock -= units;
        }
    }

    public void incrementStock(Integer units){
        this.stock += units;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return id.equals(article.id) && name.equals(article.name) && stock.equals(article.stock);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, stock);
    }
}
