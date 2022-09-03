package com.manriquecms.warehouse.infrastructure.repository;

import com.manriquecms.warehouse.domain.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends CrudRepository<Product,String> {
}
