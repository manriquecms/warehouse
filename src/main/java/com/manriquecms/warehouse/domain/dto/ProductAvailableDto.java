package com.manriquecms.warehouse.domain.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductAvailableDto {
    private String productName;
    private Integer quantity;
}
