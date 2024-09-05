package com.API.REST.controlador;

import com.API.REST.modelo.Sexo;
import com.API.REST.servicios.SexoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/sexoAPI")

public class SexoController {
    @Autowired
    SexoService sexoService;

    @GetMapping("/todoSexos")
    public List<Sexo> getAllSexos() {
        return sexoService.findAllSexos();
    }

    @GetMapping("/sexos")
    public List<Sexo> getAllSexoActivos() {
        return sexoService.findAllSexosActivos();
    }


    @PostMapping("/sexos")
    public Sexo postSexo(@Valid @RequestBody Sexo sexo) {
        return sexoService.createSexo(sexo);
    }

    @PutMapping("/sexos/{id}")
    public Sexo putSexo(@PathVariable(value = "id") Integer sexoId,
                              @Valid @RequestBody Sexo sexoDetails) {
        return sexoService.updateSexo(sexoId, sexoDetails);
    }

    @DeleteMapping("/sexos/{id}")
    public ResponseEntity<?> deleteSexo(@PathVariable(value = "id") Integer sexoId) {
        return sexoService.softDeleteSexo(sexoId);
    }
}
