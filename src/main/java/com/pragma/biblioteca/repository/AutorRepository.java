package com.pragma.biblioteca.repository;

import com.pragma.biblioteca.entity.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutorRepository extends JpaRepository<Autor,Long> {
    boolean existsByPseudonimo(String pseudonimo);
    boolean existsByEmail(String email);
}
