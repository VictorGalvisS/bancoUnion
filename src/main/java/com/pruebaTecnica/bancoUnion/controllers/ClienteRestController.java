package com.pruebaTecnica.bancoUnion.controllers;

import com.pruebaTecnica.bancoUnion.models.dto.Cliente;
import com.pruebaTecnica.bancoUnion.service.IClienteService;
import com.pruebaTecnica.bancoUnion.web.validation.excepcion.ValidationDataException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

//@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
public class ClienteRestController {

    private final IClienteService clienteService;

    public ClienteRestController(IClienteService clienteService) {
        this.clienteService = clienteService;
    }

    private final Logger log = LoggerFactory.getLogger(ClienteRestController.class);

    @GetMapping("/clientes")
    public List<Cliente> index() {
        return clienteService.findAll();
    }

    @GetMapping("/clientes/page/{page}")
    public List<Cliente> index(@PathVariable Integer page) {
        return clienteService.findAll(page);
    }
    @GetMapping("/clientes/{id}")
    public Cliente show(@PathVariable @Min(1) @Max(999999999) Long id) throws DataAccessException, ValidationDataException {
        Cliente cliente = null;
        try {
            cliente = clienteService.findCliente(id);
        } catch (DataAccessException e) {
            String msg = "Error al realizar la consulta en la base de datos ";
            throw new ValidationDataException(msg, e);
        }

        if (cliente == null) {
            String msg = "mensaje El cliente ID: ".concat(id.toString().concat(" no existe en la base de datos!"));
            throw new ValidationDataException(msg);
        }
        return cliente;
    }
    @PostMapping("/clientes")
    public Cliente create(@Valid @RequestBody Cliente cliente, BindingResult result) throws DataAccessException, ValidationDataException {
        Cliente clienteNew = null;
        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .collect(Collectors.toList());
            String commaSeparatedString = String.join(" , ", errors);
            throw new ValidationDataException(commaSeparatedString);
        }

        try {
            clienteNew = clienteService.save(cliente);
        } catch (DataAccessException e) {
            String msg = "Error al realizar el insert en la base de datos ";
            throw new ValidationDataException(msg, e);
        }

        return clienteNew;
    }
    @PutMapping("/clientes/{id}")
    public Cliente update(@Valid @RequestBody Cliente cliente, BindingResult result, @PathVariable @Min(1) @Max(999999999) Long id) throws DataAccessException, ValidationDataException {
        Cliente clienteActual = clienteService.findCliente(id);
        Cliente clienteUpdated = null;
        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .collect(Collectors.toList());
            String commaSeparatedString = String.join(" , ", errors);
            throw new ValidationDataException(commaSeparatedString);
        }

        if (clienteActual == null) {
            String commaSeparatedString = "Error: no se pudo editar, el cliente ID: "
                    .concat(id.toString().concat(" no existe en la base de datos!"));
            throw new ValidationDataException(commaSeparatedString);
        }

        try {
            clienteActual = Cliente.newInstanceClienteUpdate(clienteActual, cliente);
            if(clienteActual != null) {
                clienteUpdated = clienteService.save(clienteActual);
            }

        } catch (DataAccessException e) {
            String msg = "Error al actualizar el cliente en la base de datos ";
            throw new ValidationDataException(msg, e);
        }
        return clienteUpdated;
    }
    @DeleteMapping("/clientes/{id}")
    public boolean delete(@PathVariable @Min(1) @Max(999999999) Long id) throws DataAccessException, ValidationDataException {
        boolean eliminadoExitoso = false;
        try {
            clienteService.delete(id);
            eliminadoExitoso = true;
        } catch (DataAccessException e) {
            String msg = "Error al eliminar el cliente de la base de datos ";
            throw new ValidationDataException(msg, e);
        }
        return eliminadoExitoso;
    }
}
