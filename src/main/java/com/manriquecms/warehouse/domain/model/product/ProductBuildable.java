package com.manriquecms.warehouse.domain.model.product;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ProductBuildable {
    @Id
    private String productId;
    private Integer quantity;

    public ProductBuildable() {
    }

    public ProductBuildable(String productId, Integer quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
