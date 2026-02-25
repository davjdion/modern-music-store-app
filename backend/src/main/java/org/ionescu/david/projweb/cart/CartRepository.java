package org.ionescu.david.projweb.cart;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartRepository extends ListCrudRepository<CartItem, Integer> {

    @Query("SELECT c.id, c.product_id, p.name, p.description, p.img, c.cnt, s.unit_price, c.user_id, u.first_name, u.last_name " +
            "FROM CART_ITEM c " +
            "JOIN PRODUCT p ON p.id = c.product_id " +
            "JOIN STOCK s ON s.product_id = c.product_id " +
            "JOIN \"USER\" u ON u.id = c.user_id " +
            "WHERE c.user_id = :userId")
    List<CartFullDTO> findCartItemsForUsers(@Param("userId") Integer userId);

}
