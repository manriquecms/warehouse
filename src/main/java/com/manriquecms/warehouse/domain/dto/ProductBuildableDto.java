package com.manriquecms.warehouse.domain.dto;

import com.manriquecms.warehouse.domain.model.Product;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductBuildableDto {
    private String productName;
    private Integer quantity;
}
