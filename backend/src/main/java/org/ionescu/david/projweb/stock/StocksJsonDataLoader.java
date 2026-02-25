package org.ionescu.david.projweb.stock;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.asm.TypeReference;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Component
public class StocksJsonDataLoader implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(StocksJsonDataLoader.class);
    private final StockRepository stockRepository;
    private final ObjectMapper objectMapper;

    public StocksJsonDataLoader(StockRepository stockRepository, ObjectMapper objectMapper) {
        this.stockRepository = stockRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public void run(String... args) throws RuntimeException {
        if (stockRepository.count() == 0) {
            log.info("Loading stocks from JSON file");
            try (InputStream inputStream = TypeReference.class.getResourceAsStream("/data/stocks.json")) {
                Stocks stocks = objectMapper.readValue(inputStream, Stocks.class);
                stockRepository.saveAll(stocks.stocks());
            } catch (IOException ioex) {
                throw new RuntimeException("Failed to load stocks from JSON file", ioex);
            }
        } else {
            log.info("Stocks already loaded");
        }
    }
}
