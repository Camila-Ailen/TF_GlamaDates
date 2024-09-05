package com.API.REST.usuario;

import com.API.REST.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/usuarioAPI")

public class ControladorUsuario {
    @Autowired ServicioUsuario servicioUsuario;

    @GetMapping("/todoUsuarios")
    public List<Usuario> getAllUsuarios() {
        return servicioUsuario.findAllUsuarios();
    }

    @GetMapping("/usuarios")
    public List<Usuario> getAllUsuariosActivos() {
        return servicioUsuario.findAllUsuariosActivos();
    }

    @GetMapping("/usuarios/{id}")
    public Usuario getUsuarioById(@PathVariable(value = "id") Integer usuarioId) {
        return servicioUsuario.findUsuarioById(usuarioId);
    }

    @PostMapping("/usuarios")
    public Usuario postUsuario(@Valid @RequestBody Usuario usuario) {
        return servicioUsuario.createUsuario(usuario);
    }

    @PutMapping("/usuarios/{id}")
    public Usuario putUsuario(@PathVariable(value = "id") Integer usuarioId,
                              @Valid @RequestBody Usuario usuarioDetails) {
        return servicioUsuario.updateUsuario(usuarioId, usuarioDetails);
    }

    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<?> deleteUsuario(@PathVariable(value = "id") Integer usuarioId) {
        return servicioUsuario.softDeleteUsuario(usuarioId);
    }

}
