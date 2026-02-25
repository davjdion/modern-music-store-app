package org.ionescu.david.projweb.cart;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.asm.TypeReference;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Component
public class CartJsonDataLoader implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(CartJsonDataLoader.class);
    private final CartRepository cartRepository;
    private final ObjectMapper objectMapper;

    public CartJsonDataLoader(CartRepository cartRepository, ObjectMapper objectMapper) {
        this.cartRepository = cartRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public void run(String... args) throws RuntimeException {
        if (cartRepository.count() == 0) {
            log.info("Loading cart from JSON file");
            try (InputStream inputStream = TypeReference.class.getResourceAsStream("/data/cart.json")) {
                CartItems cartItems = objectMapper.readValue(inputStream, CartItems.class);
                cartRepository.saveAll(cartItems.cartItems());
            } catch (IOException ioex) {
                throw new RuntimeException("Failed to load cart from JSON file", ioex);
            }
        } else {
            log.info("Cart already loaded");
        }
    }
}
