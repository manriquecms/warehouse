package com.manriquecms.warehouse.domain.model;


import org.javamoney.moneta.Money;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity(name = "Product")
@Table(name = "product")
public class Product {
    @Id
    private String name;
    private String price;

    @OneToMany(
            mappedBy = "product",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<ProductArticle> articles = new ArrayList<>();

    public Product() {
        price = Money.of(1, "EUR").toString();
    }

    public Product(String name) {
        this.name = name;
        price = Money.of(1, "EUR").toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public List<ProductArticle> getArticles() {
        return articles;
    }
    public void addArticle(Article article, Integer amount) {
        ProductArticle productArticle = new ProductArticle(this,article);
        productArticle.setAmount(amount);
        articles.add(productArticle);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return name.equals(product.name) && price.equals(product.price) && articles.equals(product.articles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, articles);
    }
}
