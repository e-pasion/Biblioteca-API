package com.pragma.biblioteca.repository;

import com.pragma.biblioteca.entity.Ubicacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UbicacionRepository extends JpaRepository<Ubicacion,Long> {
    boolean existsByPiso(String piso);
    boolean existsBySalon(String salon);
    boolean existsByEstante(String estante);
}
