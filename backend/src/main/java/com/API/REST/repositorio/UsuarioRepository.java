package com.API.REST.repositorio;

import com.API.REST.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    //funcion para determinar si un correo existe
    boolean existsByCorreo(String correo);



}
