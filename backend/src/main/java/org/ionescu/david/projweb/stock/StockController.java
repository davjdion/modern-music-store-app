package org.ionescu.david.projweb.stock;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/stocks")
public class StockController {

    private StockRepository stockRepository;

    public StockController(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @GetMapping("")
    List<Stock> findAll() {
        return stockRepository.findAll();
    }

    @GetMapping("{id}")
    Stock findById(@PathVariable Integer id) {
        Optional<Stock> e = stockRepository.findById(id);
        if (e.isEmpty()) {
            throw new StockNotFoundException();
        }
        return e.get();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    void create(@Valid @RequestBody Stock body) {
        stockRepository.save(body);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("{id}")
    void update(@Valid @PathVariable Integer id, @RequestBody Stock body) {
        Optional<Stock> e = stockRepository.findById(id);
        stockRepository.save(body);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    void delete(@PathVariable Integer id) {
        stockRepository.delete(stockRepository.findById(id).orElseThrow(StockNotFoundException::new));
    }

    @GetMapping("product_id/{productId}")
    List<Stock> findByProductId(@PathVariable Integer productId) {
        return stockRepository.findByProductId(productId);
    }
}
