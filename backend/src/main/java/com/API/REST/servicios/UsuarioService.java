package com.API.REST.servicios;

import com.API.REST.exception.ResourceAlreadyExistsException;
import com.API.REST.exception.ResourceNotFoundException;
import com.API.REST.modelo.Sexo;
import com.API.REST.modelo.Usuario;
import com.API.REST.repositorio.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<Usuario> findAllUsuariosSorted(String sortField, String sortDir) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDir), sortField);
        return usuarioRepository.findAll(sort);
    }

    public List<Usuario> findAllUsuariosFiltered(Boolean activo, String rol, Sexo sexo) {
        List<Usuario> usuariosFiltrados = usuarioRepository.findAll();

        if (activo != null) {
            usuariosFiltrados = usuariosFiltrados.stream()
                    .filter(usuario -> usuario.isActivo() == activo)
                    .collect(Collectors.toList());
        }

        if (rol != null && !rol.isEmpty()) {
            usuariosFiltrados = usuariosFiltrados.stream()
                    .filter(usuario -> usuario.getUnRol().getNombre().equals(rol))
                    .collect(Collectors.toList());
        }

        if (sexo != null) {
            usuariosFiltrados = usuariosFiltrados.stream()
                    .filter(usuario -> usuario.getSexo() == sexo)
                    .collect(Collectors.toList());
        }

        return usuariosFiltrados;
    }

    public List<Usuario> sortUsuarios(List<Usuario> usuarios, String sortField, String sortDir) {
        return usuarios.stream()
                .sorted((u1, u2) -> {
                    int result = 0;
                    switch (sortField) {
                        case "id":
                            result = Integer.compare(u1.getId(), u2.getId());
                            break;
                        case "nombre":
                            result = u1.getNombre().compareTo(u2.getNombre());
                            break;
                        case "apellido":
                            result = u1.getApellido().compareTo(u2.getApellido());
                            break;
                        case "correo":
                            result = u1.getCorreo().compareTo(u2.getCorreo());
                            break;
                        case "telefono":
                            result = u1.getTelefono().compareTo(u2.getTelefono());
                            break;
                        case "fechaNacimiento":
                            result = u1.getFechaNacimiento().compareTo(u2.getFechaNacimiento());
                            break;
                        case "activo":
                            result = Boolean.compare(u1.isActivo(), u2.isActivo());
                            break;
                        case "sexo":
                            result = u1.getSexo().compareTo(u2.getSexo());
                            break;
                        case "rol":
                            result = u1.getUnRol().getNombre().compareTo(u2.getUnRol().getNombre());
                            break;
                    }
                    return sortDir.equals("asc") ? result : -result;
                })
                .collect(Collectors.toList());
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

    public Page<Usuario> getPaginatedUsuarios(List<Usuario> usuarios, PageRequest pageRequest) {
        int start = (int) pageRequest.getOffset();
        int end = Math.min((start + pageRequest.getPageSize()), usuarios.size());
        return new PageImpl<>(usuarios.subList(start, end), pageRequest, usuarios.size());
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



    public void updateUsuario(int usuarioId, Usuario usuarioDetails) {
        usuarioRepository.findById(usuarioId)
                .ifPresent(usuarioObtenido -> {
                    usuarioObtenido.setNombre(usuarioDetails.getNombre());
                    usuarioObtenido.setApellido(usuarioDetails.getApellido());
                    usuarioObtenido.setFechaNacimiento(usuarioDetails.getFechaNacimiento());
                    usuarioObtenido.setTelefono(usuarioDetails.getTelefono());
                    usuarioObtenido.setActivo(usuarioDetails.isActivo());
                    usuarioObtenido.setSexo(usuarioDetails.getSexo());
                    usuarioObtenido.setUnRol(usuarioDetails.getUnRol());
                    if (!(usuarioObtenido.getCorreo().equals(usuarioDetails.getCorreo()))){
                        if (usuarioRepository.existsByCorreo(usuarioDetails.getCorreo())) {
                            throw new ResourceNotFoundException("Usuario", "correo", usuarioDetails.getCorreo());
                        } else {
                            usuarioObtenido.setCorreo(usuarioDetails.getCorreo());
                        }
                    }
                    usuarioRepository.save(usuarioObtenido);
                });

    }

    public void softDeleteUsuario (int id) {
        usuarioRepository.findById(id).
                ifPresent(usuarioObtenido -> {
                    usuarioObtenido.setActivo(false);
                    usuarioRepository.save(usuarioObtenido);
                });
    }

    public void activarUsuario (int id) {
        usuarioRepository.findById(id).
                ifPresent(usuarioObtenido -> {
                    usuarioObtenido.setActivo(true);
                    usuarioRepository.save(usuarioObtenido);
                });
    }
/*
    public void actualizarPersonaPorId(int id, Persona persona) {
        personaRepositorio.findById(id).
                ifPresent(personaObtenida -> {
                    personaObtenida.setApellidos(persona.getApellidos());
                    personaObtenida.setNombres(persona.getNombres());
                    personaRepositorio.save(personaObtenida);
                });
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
