package com.demo.thecamanager.repositories;

import com.demo.thecamanager.entities.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LoanRepository extends JpaRepository<Loan, UUID> {
}