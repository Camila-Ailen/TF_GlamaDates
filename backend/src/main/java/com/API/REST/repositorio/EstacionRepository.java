package com.API.REST.repositorio;

import com.API.REST.modelo.Estacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EstacionRepository extends JpaRepository<Estacion, Long> {

    @Query("SELECT e FROM Estacion e JOIN e.listaCategorias c WHERE c.id = :categoriaId AND e.estado = 'OPERATIVA' AND e.id NOT IN (SELECT t.unaEstacion.id FROM Turno t WHERE t.fecha = :fecha AND t.horario.id = :horarioId)")
    List<Estacion> findEstacionesDisponibles(@Param("categoriaId") Long categoriaId, @Param("fecha") LocalDate fecha, @Param("horarioId") Long horarioId);
}