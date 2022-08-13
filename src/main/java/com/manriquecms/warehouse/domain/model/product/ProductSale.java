package com.manriquecms.warehouse.domain.model.product;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Calendar;

@Entity
public class ProductSale {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Calendar createDate;

    private String productId;

    private Integer quantity;

    public ProductSale() {
        this.createDate = Calendar.getInstance();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Calendar getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Calendar createDate) {
        this.createDate = createDate;
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
