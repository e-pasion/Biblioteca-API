package com.pragma.biblioteca.repository;

import com.pragma.biblioteca.entity.Pais;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaisRepository extends JpaRepository<Pais,String> {
    boolean existsByNombre(String nombre);
    Optional<Pais> findByNombre(String nombre);
}
