package com.API.REST.usuario;

import com.API.REST.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;

@Service
public class ServicioUsuario {

    @Autowired
    private RepositorioUsuario repositorioUsuario;

    public List<Usuario> findAllUsuarios() {
        return repositorioUsuario.findAll();
    }

    public List<Usuario> findAllUsuariosActivos() {
        var usuarios = this.repositorioUsuario.findAll();
        var listado = new ArrayList<Usuario>();
        for (var usuario : usuarios) {
            if (usuario.isActivo()) {
                listado.add(usuario);
            }
        }
        return listado;
    }


    public Usuario findUsuarioById(Integer usuarioId) {
        return repositorioUsuario.findById(usuarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario", "id", usuarioId));
    }

    public Usuario createUsuario(Usuario usuario) {
        return repositorioUsuario.save(usuario);
    }

    public Usuario updateUsuario(Integer usuarioId, Usuario usuarioDetails) {

        Usuario usuario = repositorioUsuario.findById(usuarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario", "id", usuarioId));

        usuario.setNombre(usuarioDetails.getNombre());
        usuario.setApellido(usuarioDetails.getApellido());
        usuario.setFechaNacimiento(usuarioDetails.getFechaNacimiento());
        usuario.setClave(usuarioDetails.getClave());
        usuario.setTelefono(usuarioDetails.getTelefono());
        usuario.setActivo(usuarioDetails.isActivo());

        if (!(usuario.getCorreo().equals(usuarioDetails.getCorreo()))){
            if (repositorioUsuario.existsByCorreo(usuarioDetails.getCorreo())) {
                throw new ResourceNotFoundException("Usuario", "correo", usuarioDetails.getCorreo());
            } else {
            usuario.setCorreo(usuarioDetails.getCorreo());
            }
        }


        return repositorioUsuario.save(usuario);
    }

    public ResponseEntity<?> softDeleteUsuario(Integer usuarioId) {
        Usuario usuario = repositorioUsuario.findById(usuarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario", "id", usuarioId));
        usuario.setActivo(false);
        repositorioUsuario.save(usuario);
        return ResponseEntity.ok().build();
    }
}
