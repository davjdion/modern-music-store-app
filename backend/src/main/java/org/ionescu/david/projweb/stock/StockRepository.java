package org.ionescu.david.projweb.stock;

import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface StockRepository extends ListCrudRepository<Stock, Integer> {

    List<Stock> findByProductId(Integer productId);

}
