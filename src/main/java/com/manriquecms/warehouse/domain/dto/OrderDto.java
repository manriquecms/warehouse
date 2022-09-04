package com.manriquecms.warehouse.domain.dto;

import com.manriquecms.warehouse.domain.model.WarehouseOrder;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderDto {
    private String id;
    private String createDate;
    private String fee;

    public static OrderDto fromOrder(WarehouseOrder warehouseOrder) {
        return OrderDto.builder()
                .id(warehouseOrder.getId())
                .createDate(warehouseOrder.getCreateDateAsString())
                .fee(warehouseOrder.getFee())
                .build();
    }
}
