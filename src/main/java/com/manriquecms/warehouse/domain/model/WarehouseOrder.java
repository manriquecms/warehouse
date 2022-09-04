package com.manriquecms.warehouse.domain.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.javamoney.moneta.Money;

import javax.persistence.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Entity(name = "WarehouseOrder")
@Table(name = "warehouseorder")
public class WarehouseOrder {
    @Transient
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @ApiModelProperty(hidden = true)
    private String id;

    private Timestamp createDate;

    private String fee;

    @OneToMany(
            mappedBy = "warehouseOrder",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<OrderProduct> products = new ArrayList<>();


    public void addProduct(Product product, Integer amount) {
        OrderProduct orderProduct = new OrderProduct(this,product);
        orderProduct.setAmount(amount);
        products.add(orderProduct);
    }

    public WarehouseOrder() {
        createDate = Timestamp.valueOf(LocalDateTime.now());
        fee = Money.of(0, "EUR").toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public String getCreateDateAsString() {
        return dateTimeFormatter.format(createDate.toLocalDateTime());
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public List<OrderProduct> getProducts() {
        return products;
    }

    public void setProducts(List<OrderProduct> products) {
        this.products = products;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public void sumFee(String other) {
        fee = Money.parse(fee).add(Money.parse(other)).toString();
    }
}
