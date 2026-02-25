package org.ionescu.david.projweb.cart;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("CART_ITEM")
public record CartItem(
    @Id
    Long id,
    @NotNull
    Integer userId,
    @NotNull
    Integer productId,
    @NotNull
    Integer cnt
) {}
