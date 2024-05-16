package com.demo.thecamanager.repositories;

import com.demo.thecamanager.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID> {
    boolean existsByTitleContainsIgnoreCase(String title);

    List<Book> findByUser_CpfOrderByTitleAsc(String cpf);

    Optional<Book> findByUser_Cpf(String cpf);

    Optional<Book> findByIdAndUser_Cpf(@NonNull UUID id, @NonNull String cpf);
}