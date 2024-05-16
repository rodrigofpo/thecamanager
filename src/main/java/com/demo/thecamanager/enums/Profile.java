package com.demo.thecamanager.enums;

import lombok.Getter;

@Getter
public enum Profile {
    LIBRARIAM("bibliotecário"),
    ADMIN("administrador"),
    CLIENT("cliente");

    private final String description;

    Profile(String description) {
        this.description = description;
    }
}