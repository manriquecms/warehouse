package com.manriquecms.warehouse.service.configuration;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.manriquecms.warehouse.domain.model.Product;
import com.manriquecms.warehouse.infrastructure.repository.ArticleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class ProductInitializer {
    private static Logger logger = LoggerFactory.getLogger(ProductInitializer.class);
    @Value("${warehouse.catalog.json.path}")
    private String catalogJsonPath;
    @Value("${warehouse.catalog.root.value}")
    private String catalogRootValue;

    @Autowired
    private ArticleRepository articleRepository;

    public ProductInitializer(ArticleRepository articleRepository){
        this.articleRepository = articleRepository;
    }

    public List<Product> readProductsFromJson() {
        List<Product> products = null;

        SimpleModule module =
                new SimpleModule("CustomProductDeserializer", new Version(1, 0, 0, null, null, null));
        module.addDeserializer(Product.class, new CustomProductDeserializer(articleRepository));
        //Car car = mapper.readValue(json, Car.class);

        // read json and write to db
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(module);

        mapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, false);

        TypeReference<List<Product>> typeReference = new TypeReference<>(){};
        InputStream inputStream = TypeReference.class.getResourceAsStream(catalogJsonPath);
        try {
            logger.info("Reading products from Json");
            products = mapper.readerFor(typeReference)
                    .withRootName(catalogRootValue)
                    .readValue(inputStream);

            logger.info("Read success");
        } catch (IOException e){
            logger.error("Unable to read products: " + e.getMessage());
        }
        return products;
    }



}
