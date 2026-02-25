package org.ionescu.david.projweb.product;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.asm.TypeReference;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Component
public class ProductsJsonDataLoader implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(ProductsJsonDataLoader.class);
    private final ProductRepository productRepository;
    private final ObjectMapper objectMapper;

    public ProductsJsonDataLoader(ProductRepository productRepository, ObjectMapper objectMapper) {
        this.productRepository = productRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public void run(String... args) throws RuntimeException {
        if (productRepository.count() == 0) {
            log.info("Loading products from JSON file");
            try (InputStream inputStream = TypeReference.class.getResourceAsStream("/data/products.json")) {
                Products products = objectMapper.readValue(inputStream, Products.class);
                productRepository.saveAll(products.products());
            } catch (IOException ioex) {
                throw new RuntimeException("Failed to load products from JSON file", ioex);
            }
        } else {
            log.info("Products already loaded");
        }
    }
}
