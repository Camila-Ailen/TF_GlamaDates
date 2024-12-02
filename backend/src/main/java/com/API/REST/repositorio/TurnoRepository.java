package com.API.REST.repositorio;

import com.API.REST.modelo.Turno;
import java.util.List;
import com.API.REST.modelo.TurnoEstado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TurnoRepository extends JpaRepository<Turno, Long> {
    List<Turno> findByClienteIdAndEstado(Long clienteId, TurnoEstado estado);

}