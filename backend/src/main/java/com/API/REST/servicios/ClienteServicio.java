package com.API.REST.servicios;

import com.API.REST.exception.ResourceNotFoundException;
import com.API.REST.modelo.Cliente;
import com.API.REST.repositorio.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;

@Service
public class ClienteServicio {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> findAllClientes() {
        return clienteRepository.findAll();
    }

    public List<Cliente> findAllClientesActivos() {
        var clientes = this.clienteRepository.findAll();
        var listado = new ArrayList<Cliente>();
        for (var cliente : clientes) {
            if (cliente.isActivo()) {
                listado.add(cliente);
            }
        }
        return listado;
    }


    public Cliente findClienteById(Long clienteId) {
        return clienteRepository.findById(String.valueOf(clienteId))
                .orElseThrow(() -> new ResourceNotFoundException("Cliente", "id", clienteId));
    }

    public Cliente createCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Cliente updateCliente(Long clienteId, Cliente clienteDetails) {

        Cliente cliente = clienteRepository.findById(String.valueOf(clienteId))
                .orElseThrow(() -> new ResourceNotFoundException("Cliente", "id", clienteId));

        cliente.setNombre(clienteDetails.getNombre());
        cliente.setApellido(clienteDetails.getApellido());
        cliente.setFechaNacimiento(clienteDetails.getFechaNacimiento());
        cliente.setFechaIngreso(clienteDetails.getFechaIngreso());
        cliente.setEstatura(clienteDetails.getEstatura());
        cliente.setPeso(clienteDetails.getPeso());
        cliente.setSexo(clienteDetails.getSexo());
        cliente.setActivo(clienteDetails.isActivo());
        return clienteRepository.save(cliente);
    }

    public ResponseEntity<?> softDeleteCliente(Long clienteId) {
        Cliente cliente = clienteRepository.findById(String.valueOf(clienteId))
                .orElseThrow(() -> new ResourceNotFoundException("Cliente", "id", clienteId));
        cliente.setActivo(false);
        clienteRepository.save(cliente);
        return ResponseEntity.ok().build();
    }
}
