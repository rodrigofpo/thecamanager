package com.demo.thecamanager.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

/**
 * DTO for {@link com.demo.thecamanager.entities.User}
 */
public record UserDTO(@NotNull @Size(max = 11) String cpf) implements Serializable {
}