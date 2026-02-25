package org.ionescu.david.projweb.user;

import jakarta.validation.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("USER")
public record User(
    @Id
    Integer id,
    @NotEmpty
    String firstName,
    @NotEmpty
    String lastName,
    @NotEmpty
    String email,
    @NotEmpty
    String password,
    @NotEmpty
    Integer age,
    String address
) {}
