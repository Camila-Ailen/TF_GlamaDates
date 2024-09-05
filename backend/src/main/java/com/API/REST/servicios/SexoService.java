package com.API.REST.servicios;

import com.API.REST.exception.ResourceNotFoundException;
import com.API.REST.modelo.Sexo;
import com.API.REST.repositorio.SexoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SexoService {
    @Autowired
    private SexoRepository sexoRepository;

    public List<Sexo> findAllSexos() {
        return sexoRepository.findAll();
    }

    public List<Sexo> findAllSexosActivos() {
        var sexos = this.sexoRepository.findAll();
        var listado = new ArrayList<Sexo>();
        for (var sexo : sexos) {
            if (sexo.isActivo()) {
                listado.add(sexo);
            }
        }
        return listado;
    }


    public Sexo createSexo(Sexo sexo) {
        return sexoRepository.save(sexo);
    }

    public Sexo updateSexo(Integer sexoId, Sexo sexoDetails) {

        Sexo sexo = sexoRepository.findById(sexoId)
                .orElseThrow(() -> new ResourceNotFoundException("Sexo", "id", sexoId));

        sexo.setValor(sexoDetails.getValor());
        sexo.setActivo(sexoDetails.isActivo());

        return sexoRepository.save(sexo);
    }

    public ResponseEntity<?> softDeleteSexo(Integer sexoId) {
        Sexo sexo = sexoRepository.findById(sexoId)
                .orElseThrow(() -> new ResourceNotFoundException("Sexo", "id", sexoId));
        sexo.setActivo(false);
        sexoRepository.save(sexo);
        return ResponseEntity.ok().build();
    }
}
