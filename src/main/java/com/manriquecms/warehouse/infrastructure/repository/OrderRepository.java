package com.manriquecms.warehouse.infrastructure.repository;

import com.manriquecms.warehouse.domain.model.WarehouseOrder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<WarehouseOrder,String> {
}
