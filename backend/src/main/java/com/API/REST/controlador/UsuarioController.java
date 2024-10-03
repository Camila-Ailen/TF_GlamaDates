package com.API.REST.controlador;

import com.API.REST.modelo.Usuario;
import com.API.REST.servicios.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@CrossOrigin
@RequestMapping("/usuarioAPI")
@RequiredArgsConstructor
public class UsuarioController {
    @Autowired
    private final UsuarioService usuarioService;

    @GetMapping("/todoUsuarios")
    public List<Usuario> getAllUsuarios() {
        return usuarioService.findAllUsuarios();
    }


    @GetMapping("/usuarios")
    public List<Usuario> getAllUsuariosActivos() {
        return usuarioService.findAllUsuariosActivos();
    }

    @GetMapping("/usuarios/{id}")
    public Usuario getUsuarioById(@PathVariable(value = "id") Integer usuarioId) {
        return usuarioService.findUsuarioById(usuarioId);
    }

    @PostMapping("/usuarios")
    public Usuario postUsuario(@Valid @RequestBody Usuario usuario) {
        return usuarioService.saveUsuario(usuario);
    }

/*
    @PutMapping("/usuarios/{id}")
    public Usuario putUsuario(@PathVariable(value = "id") Integer usuarioId,
                              @Valid @RequestBody Usuario usuarioDetails) {
        return usuarioService.updateUsuario(usuarioId, usuarioDetails);
    }


    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<?> deleteUsuario(@PathVariable(value = "id") Integer usuarioId) {
        return usuarioService.softDeleteUsuario(usuarioId);
    }

*/

}
