package com.API.REST.servicios;

import com.API.REST.modelo.Servicio;
import com.API.REST.repositorio.ServicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioService {

    @Autowired
    private ServicioRepository servicioRepository;

    public List<Servicio> findServiciosByPaqueteId(Long paqueteId) {
        return servicioRepository.findByPaquetesId(paqueteId);
    }
}