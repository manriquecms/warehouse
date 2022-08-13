package com.manriquecms.warehouse.domain.model.product;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class ProductPart {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

    private String art_id;
    private Integer amount_of;

    public ProductPart() {}

    public ProductPart(String art_id, Integer amount_of) {
        this.art_id = art_id;
        this.amount_of = amount_of;
    }

    public String getArt_id() {
        return art_id;
    }

    public void setArt_id(String art_id) {
        this.art_id = art_id;
    }

    public Integer getAmount_of() {
        return amount_of;
    }

    public void setAmount_of(String amount_of) throws NumberFormatException{
        this.amount_of = Integer.parseInt(amount_of);
    }

}
