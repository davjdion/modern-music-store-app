package org.ionescu.david.projweb.product;

import jakarta.validation.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("PRODUCT")
public record Product(
    @Id
    Long id,
    @NotEmpty
    String name,
    String description,
    @NotEmpty
    String img
) {}
