package com.manriquecms.warehouse.domain.model.product;

import javax.persistence.Entity;
import javax.persistence.Id;

public class ProductBuildable {
    private Product product;
    private Integer quantity;

    public ProductBuildable() {
    }

    public ProductBuildable(Product product, Integer quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
