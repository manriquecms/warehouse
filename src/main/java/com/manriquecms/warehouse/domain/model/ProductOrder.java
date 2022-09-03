package com.manriquecms.warehouse.domain.model;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Calendar;

@Entity
public class ProductOrder {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @ApiModelProperty(hidden = true)
    private String id;

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @ApiModelProperty(hidden = true)
    private java.util.Calendar createDate;
//Instance && Time

    private String productId;

    private Integer quantity;

    public ProductOrder () {
        this.createDate = Calendar.getInstance();
    }

    public ProductOrder (String productId, Integer quantity) {
        this.createDate = Calendar.getInstance();
        this.productId = productId;
        this.quantity = quantity;
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
