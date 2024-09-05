package com.API.REST.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioUsuario extends JpaRepository<Usuario, Integer> {

    //funcion para determinar si un correo existe
    boolean existsByCorreo(String correo);



}
