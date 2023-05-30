package com.pragma.biblioteca.repository;

import com.pragma.biblioteca.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria,Long> {
    boolean existsByNombre(String nombre);
}
