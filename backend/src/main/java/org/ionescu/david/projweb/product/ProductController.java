package org.ionescu.david.projweb.product;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("")
    List<Product> findAll() {
        return productRepository.findAll();
    }

    @GetMapping("{id}")
    Product findById(@PathVariable Integer id) {
        Optional<Product> e = productRepository.findById(id);
        if (e.isEmpty()) {
            throw new ProductNotFoundException();
        }
        return e.get();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    void create(@Valid @RequestBody Product body) {
        productRepository.save(body);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("{id}")
    void update(@Valid @PathVariable Integer id, @RequestBody Product body) {
        Optional<Product> e = productRepository.findById(id);
        productRepository.save(body);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    void delete(@PathVariable Integer id) {
        productRepository.delete(productRepository.findById(id).orElseThrow(ProductNotFoundException::new));
    }

    @GetMapping("name/{name}")
    List<Product> findByName(@PathVariable String name) {
        return productRepository.findByName(name);
    }

    @GetMapping("with-stock")
    List<ProductWithStockDTO> findAllProductsWithStock() {
        return productRepository.findAllProductsWithStock();
    }
}
