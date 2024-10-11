package com.API.REST.servicios;

import com.API.REST.exception.ResourceAlreadyExistsException;
import com.API.REST.exception.ResourceNotFoundException;
import com.API.REST.modelo.Usuario;
import com.API.REST.repositorio.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {


    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public List<Usuario> findAllUsuarios() {
        var usuarios = this.usuarioRepository.findAll();
        System.out.println("Estos son los usuarios desde el servicio: " + usuarios);
        return usuarios;
    }


    public List<Usuario> findAllUsuariosActivos() {
        return usuarioRepository.findByActivo(true);
        /*
        var usuarios = this.usuarioRepository.findAll();
        var listado = new ArrayList<Usuario>();
        for (var usuario : usuarios) {
            if (usuario.isActivo()) {
                listado.add(usuario);
            }
        }
        return listado;

         */
    }



    public Usuario findUsuarioById(Integer usuarioId) {
        return usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario", "id", usuarioId));
    }

    public Usuario saveUsuario(Usuario usuario) {
        if (usuarioRepository.existsByCorreo(usuario.getCorreo())) {
            throw new ResourceAlreadyExistsException("Usuario", "correo", usuario.getCorreo());
        } else {
            usuario.setClave(passwordEncoder.encode(usuario.getClave()));
            return usuarioRepository.save(usuario);
        }
    }


    /*
    public Usuario updateUsuario(Integer usuarioId, Usuario usuarioDetails) {

        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario", "id", usuarioId));

        usuario.setNombre(usuarioDetails.getNombre());
        usuario.setApellido(usuarioDetails.getApellido());
        usuario.setFechaNacimiento(usuarioDetails.getFechaNacimiento());
        usuario.setClave(usuarioDetails.getClave());
        usuario.setTelefono(usuarioDetails.getTelefono());
        usuario.setActivo(usuarioDetails.isActivo());
        usuario.setSexo(usuarioDetails.getSexo());


        if (!(usuario.getCorreo().equals(usuarioDetails.getCorreo()))){
            if (usuarioRepository.existsByCorreo(usuarioDetails.getCorreo())) {
                throw new ResourceNotFoundException("Usuario", "correo", usuarioDetails.getCorreo());
            } else {
            usuario.setCorreo(usuarioDetails.getCorreo());
            }
        }
        return usuarioRepository.save(usuario);
    }



    public ResponseEntity<?> softDeleteUsuario(Integer usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario", "id", usuarioId));
        usuario.setActivo(false);
        usuarioRepository.save(usuario);
        return ResponseEntity.ok().build();
    }

*/
}
