package com.manriquecms.warehouse.domain.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class OrderProductId implements Serializable {
    @Column(name = "order_id")
    private String orderId;
    @Column(name = "product_name")
    private String productName;

    public OrderProductId() {
    }

    public OrderProductId(String orderId, String productName) {
        this.orderId = orderId;
        this.productName = productName;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderProductId that = (OrderProductId) o;
        return orderId.equals(that.orderId) && productName.equals(that.productName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, productName);
    }
}
