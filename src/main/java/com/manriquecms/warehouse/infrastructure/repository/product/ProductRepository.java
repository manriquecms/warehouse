package com.manriquecms.warehouse.infrastructure.repository.article;

import com.manriquecms.warehouse.domain.model.product.Product;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends CrudRepository<Product,String> {
    @EntityGraph(attributePaths = {"contain_articles"})
    Optional<Product> findById(String id);
}
