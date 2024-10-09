package com.API.REST.servicios;

import com.API.REST.modelo.Rol;
import com.API.REST.repositorio.RolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RolService {
    private final RolRepository rolRepository;

    public List<Rol> findAllRoles() {
        return rolRepository.findAll();
    }
}
