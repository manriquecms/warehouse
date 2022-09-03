package com.manriquecms.warehouse.integration;

import com.manriquecms.warehouse.WarehouseApplication;
import com.manriquecms.warehouse.domain.model.Product;
import com.manriquecms.warehouse.domain.model.ProductArticle;
import com.manriquecms.warehouse.infrastructure.repository.ProductRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@SpringBootTest(classes = WarehouseApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductIntegrationTests {
    @LocalServerPort
    private int port;

    @Autowired
    TestRestTemplate restTemplate;
    @Autowired
    ProductRepository productRepository;

    @Test
    @Order(1)
    public void getAllProducts(){
        List<Product> products = StreamSupport.stream(productRepository.findAll().spliterator(),false)
                .collect(Collectors.toList());
        Assertions.assertEquals(products.size(), 2);
    }
}
