package com.demo.thecamanager.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "tb_punishment")
public class Punishment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @ColumnDefault("CURRENT_DATE")
    @Column(name = "punishment_init")
    private LocalDate punishmentInit;

    @Column(name = "punishment_final")
    private LocalDate punishmentFinal;

    @NotNull
    @ColumnDefault("2")
    @Column(name = "punishment_duration", nullable = false)
    private Integer punishmentDuration;

    @Column(name = "observation", length = Integer.MAX_VALUE)
    private String observation;

    @ColumnDefault("CURRENT_DATE")
    @Column(name = "created_at")
    private LocalDate createdAt;
    @OneToMany(mappedBy = "punishment")
    private Set<User> tbUsers = new LinkedHashSet<>();

/*
 TODO [Reverse Engineering] create field to map the 'punishment_type' column
 Available actions: Define target Java type | Uncomment as is | Remove column mapping
    @ColumnDefault("'atraso'::punishment_type")
    @Column(name = "punishment_type", columnDefinition = "punishment_type not null")
    private Object punishmentType;
*/
}