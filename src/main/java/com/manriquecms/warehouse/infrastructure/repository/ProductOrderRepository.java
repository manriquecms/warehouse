package com.manriquecms.warehouse.infrastructure.repository;

import com.manriquecms.warehouse.domain.model.ProductOrder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductOrderRepository extends CrudRepository<ProductOrder,String> {
}
