package com.demo.thecamanager.dto;

import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

/**
 * DTO for {@link com.demo.thecamanager.entities.Loan}
 */
public record LoanDTO(UUID id, @NotNull BookDTO book, @NotNull UserDTO user, LocalDate loanDate, LocalDate returnDate,
                      String status) implements Serializable {
}