package com.manriquecms.warehouse.service.configuration;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.manriquecms.warehouse.domain.model.product.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class CatalogInitializer {
    private static Logger logger = LoggerFactory.getLogger(CatalogInitializer.class);
    @Value("${warehouse.catalog.json.path}")
    private String catalogJsonPath;
    @Value("${warehouse.catalog.root.value}")
    private String catalogRootValue;

    public List<Product> readProductsFromJson() {
        List<Product> products = null;

        // read json and write to db
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);

        TypeReference<List<Product>> typeReference = new TypeReference<List<Product>>(){};
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
