package com.demo.thecamanager.enums;

public enum LoanStatus {
    REQUESTED("solicitado"),
    LOANED("emprestado"),
    RETURNED("devolvido"),
    LATE("atrasado"),
    MISSED("perdido");

    private final String description;

    LoanStatus(String description) {
        this.description = description;
    }
}
