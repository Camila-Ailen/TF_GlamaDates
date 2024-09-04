package com.API.REST.controlador;

import com.API.REST.exception.ResourceNotFoundException;
import com.API.REST.modelo.Cliente;
import com.API.REST.repositorio.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import com.API.REST.servicios.ClienteServicio;

@RestController
@CrossOrigin
@RequestMapping("/clienteAPI")

public class ClienteController {
    @Autowired ClienteServicio clienteServicio;

    @GetMapping("/todoClientes")
    public List<Cliente> getAllClientes() {
        return clienteServicio.findAllClientes();
    }

    @GetMapping("/clientes")
    public List<Cliente> getAllClientesActivos() {
        return clienteServicio.findAllClientesActivos();
    }

    @GetMapping("/clientes/{id}")
    public Cliente getClienteById(@PathVariable(value = "id") Long clienteId) {
        return clienteServicio.findClienteById(clienteId);
    }

    @PostMapping("/clientes")
    public Cliente postCliente(@Valid @RequestBody Cliente cliente) {
        return clienteServicio.createCliente(cliente);
    }

    @PutMapping("/clientes/{id}")
    public Cliente putCliente(@PathVariable(value = "id") Long clienteId,
                                 @Valid @RequestBody Cliente clienteDetails) {
        return clienteServicio.updateCliente(clienteId, clienteDetails);
    }

    @DeleteMapping("/clientes/{id}")
    public ResponseEntity<?> deleteCliente(@PathVariable(value = "id") Long clienteId) {
        return clienteServicio.softDeleteCliente(clienteId);
    }
}
