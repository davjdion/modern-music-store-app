package org.ionescu.david.projweb.stock;

import jakarta.validation.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("STOCK")
public record Stock(
    @Id
    Long id,
    @NotEmpty
    Integer productId,
    @NotEmpty
    Integer cnt,
    @NotEmpty
    Float unitPrice
) {}
