package com.demo.thecamanager.repositories;

import com.demo.thecamanager.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
  UserDetails findByCpf(String cpf);
}