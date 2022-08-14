package com.manriquecms.warehouse.service.command;

public class CreateOrderCommand {
    private String productId;
    private Integer quantity;

    public CreateOrderCommand(String productId, Integer quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public String getProductId() {
        return productId;
    }


    public Integer getQuantity() {
        return quantity;
    }

}
