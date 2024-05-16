package com.demo.thecamanager.enums;

import lombok.Getter;

@Getter
public enum PunishimentType {
    DELAY("atraso"),
    DAMAGE("dano"),
    MISS("perda");

    private final String description;

    PunishimentType(String description) {
        this.description = description;
    }
}