package com.demo.thecamanager.entities;

import com.demo.thecamanager.enums.LoanStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "tb_loan")
public class Loan {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @ColumnDefault("CURRENT_DATE")
    @Column(name = "loan_date")
    private LocalDate loanDate;

    @ColumnDefault("CURRENT_DATE")
    @Column(name = "return_date")
    private LocalDate returnDate;

    @ColumnDefault("CURRENT_DATE")
    @Column(name = "created_at")
    private LocalDate createdAt;

    @Column(name = "status", nullable = false)
    private String status = LoanStatus.REQUESTED.name();
}