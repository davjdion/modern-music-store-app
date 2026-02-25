package org.ionescu.david.projweb.product;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface ProductRepository extends ListCrudRepository<Product, Integer> {

    List<Product> findByName(String name);

    @Query("SELECT p.id, p.name, p.description, p.img, s.cnt, s.unit_price " +
           "FROM PRODUCT p JOIN STOCK s ON p.id = s.product_id")
    List<ProductWithStockDTO> findAllProductsWithStock();
}
