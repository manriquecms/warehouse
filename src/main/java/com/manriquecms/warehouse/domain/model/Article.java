package com.manriquecms.warehouse.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.manriquecms.warehouse.domain.model.exceptions.InitializingStockNegativeNotAllowedException;
import com.manriquecms.warehouse.domain.model.exceptions.NotEnoughStockToReduceException;
import com.manriquecms.warehouse.domain.model.exceptions.NotNumberFormatForStockQuantityException;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity(name = "Article")
@Table(name = "article")
public class Article {
    @Id
    private String id;
    private String name;
    private Integer stock;

    @OneToMany(
            mappedBy = "article",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<ProductArticle> products = new ArrayList<>();

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

    public List<ProductArticle> getProducts() {
        return products;
    }

    public void setProducts(List<ProductArticle> products) {
        this.products = products;
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
