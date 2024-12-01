package com.API.REST.repositorio;

import com.API.REST.modelo.Horario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface HorarioRepository extends JpaRepository<Horario, Long> {
    @Query("SELECT h FROM Horario h WHERE h.categoria.id = :categoriaId AND h.fecha = :fecha")
    List<Horario> findHorariosDisponibles(@Param("categoriaId") Long categoriaId, @Param("fecha") LocalDate fecha);
}
