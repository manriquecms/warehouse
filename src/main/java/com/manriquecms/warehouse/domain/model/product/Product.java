package com.manriquecms.warehouse.domain.model.product;


import org.javamoney.moneta.Money;

import javax.persistence.*;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Entity
public class Product {
    @Id
    private String name;
    @OneToMany(cascade = CascadeType.ALL)
    private List<ProductPart> contain_articles;
    private String price;

    public Product() {
        price = Money.of(1, "EUR").toString();
    }

    public Product(String name, List<ProductPart> contain_articles) {
        this.name = name;
        this.contain_articles = contain_articles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ProductPart> getContain_articles() {
        return contain_articles;
    }

    public void setContain_articles(List<ProductPart> contain_articles) {
        this.contain_articles = contain_articles;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Map<String, Integer> mapForArticlesIdsAndAmountFromProduct(){
        return this.contain_articles.stream()
                .collect(Collectors.toMap(ProductPart::getArt_id, ProductPart::getAmount_of));
    }
}
