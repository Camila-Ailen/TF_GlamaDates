package com.API.REST.repositorio;

import com.API.REST.modelo.Usuario;
import com.API.REST.modelo.Horario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>, JpaSpecificationExecutor<Usuario> {

    //@Query("SELECT u FROM Usuario u JOIN u.categorias c WHERE c.id = :categoriaId AND u.id NOT IN (SELECT t.usuario.id FROM Turno t WHERE t.fecha = :fecha)")
    //List<Usuario> findProfesionalesDisponibles(Long categoriaId, LocalDate fecha);

    //funcion para determinar si un correo existe
    boolean existsByCorreo(String correo);

    Optional<Usuario> findByCorreo(String correo);

    List<Usuario> findByActivo(boolean activo);

    Page<Usuario> findAll(Pageable pageable);

    @Query("SELECT u FROM Usuario u JOIN u.categorias c WHERE c.id = :categoriaId AND u.id NOT IN (SELECT t.usuario.id FROM Turno t WHERE t.fecha = :fecha AND t.horario.id = :horarioId)")
    List<Usuario> findProfesionalesDisponibles(@Param("categoriaId") Long categoriaId, @Param("fecha") LocalDate fecha, @Param("horarioId") Long horarioId);


}
