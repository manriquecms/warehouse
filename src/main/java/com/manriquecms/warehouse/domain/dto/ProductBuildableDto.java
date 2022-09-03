package com.manriquecms.warehouse.domain.dto;

import com.manriquecms.warehouse.domain.model.Product;

public class ProductBuildableDto {
    private String productName;
    private Integer quantity;

    public ProductBuildableDto() {
    }

    public ProductBuildableDto(String productName, Integer quantity) {
        this.productName = productName;
        this.quantity = quantity;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(Product product) {
        this.productName = productName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
