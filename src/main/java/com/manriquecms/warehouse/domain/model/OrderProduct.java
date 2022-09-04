package com.manriquecms.warehouse.domain.model;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "OrderProduct")
@Table(name = "order_product")
public class OrderProduct {
    @EmbeddedId
    private OrderProductId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("productId")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("orderId")
    private WarehouseOrder warehouseOrder;

    private Integer amount;

    public OrderProduct() {
    }
    public OrderProduct(OrderProductId id, Product product, WarehouseOrder warehouseOrder, Integer amount) {
        this.id = id;
        this.product = product;
        this.warehouseOrder = warehouseOrder;
        this.amount = amount;
    }

    public OrderProduct(WarehouseOrder warehouseOrder, Product product) {
        this.warehouseOrder = warehouseOrder;
        this.product = product;
        this.id = new OrderProductId(warehouseOrder.getId(), product.getName());
    }

    public OrderProductId getId() {
        return id;
    }

    public void setId(OrderProductId id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public WarehouseOrder getOrder() {
        return warehouseOrder;
    }

    public void setOrder(WarehouseOrder warehouseOrder) {
        this.warehouseOrder = warehouseOrder;
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
        OrderProduct that = (OrderProduct) o;
        return id.equals(that.id) && product.equals(that.product) && warehouseOrder.equals(that.warehouseOrder) && amount.equals(that.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, product, warehouseOrder, amount);
    }
}
