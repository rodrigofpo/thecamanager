package com.demo.thecamanager.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.UUID;

/**
 * DTO for {@link com.demo.thecamanager.entities.Book}
 */
public record BookDTO(UUID id, String title,String author, UserDTO user) implements Serializable {
}