package com.demo.thecamanager.repositories;

import com.demo.thecamanager.entities.Punishment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PunishmentRepository extends JpaRepository<Punishment, UUID> {
}