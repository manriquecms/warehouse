package com.manriquecms.warehouse.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "ProductArticle")
@Table(name = "product_article")
public class ProductArticle {

    @EmbeddedId
    private ProductArticleId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("productId")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("articleId")
    private Article article;

    @JsonProperty("amount_of")
    private Integer amount;

    public ProductArticle() {}

    public ProductArticle(Product product, Article article) {
        this.product = product;
        this.article = article;
        this.id = new ProductArticleId(product.getName(), article.getId());
    }

    public ProductArticleId getId() {
        return id;
    }

    public void setId(ProductArticleId id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductArticle that = (ProductArticle) o;
        return id.equals(that.id) && product.equals(that.product) && article.equals(that.article) && amount.equals(that.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, product, article, amount);
    }
}
