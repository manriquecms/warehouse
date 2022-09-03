package com.manriquecms.warehouse.domain.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ProductArticleId implements Serializable {
    @Column(name = "product_name")
    private String productName;

    @Column(name = "article_id")
    private String articleId;

    public ProductArticleId() {
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public ProductArticleId(String productName, String articleId) {
        this.productName = productName;
        this.articleId = articleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductArticleId that = (ProductArticleId) o;
        return productName.equals(that.productName) && articleId.equals(that.articleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productName, articleId);
    }
}
