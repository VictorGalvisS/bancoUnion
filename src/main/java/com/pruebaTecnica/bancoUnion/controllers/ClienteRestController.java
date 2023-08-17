package com.pruebaTecnica.bancoUnion.controllers;

import com.pruebaTecnica.bancoUnion.models.dto.Cliente;
import com.pruebaTecnica.bancoUnion.models.dto.Factura;
import com.pruebaTecnica.bancoUnion.service.IClienteService;
import com.pruebaTecnica.bancoUnion.config.validation.excepcion.ValidationDataException;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@SecurityRequirement(name = "bearer-key")
public class ClienteRestController {
    private static final Logger logger = LoggerFactory.getLogger(ClienteRestController.class);

    private final IClienteService clienteService;

    public ClienteRestController(IClienteService clienteService) {
        this.clienteService = clienteService;
    }

    private final Logger log = LoggerFactory.getLogger(ClienteRestController.class);

    @GetMapping(value = "/alive")
    public ResponseEntity<String> getVersion() {
        log.info("*********** called.. /alive");
        return ResponseEntity.ok(new Date() + " - " + "1.0.0");
    }

    @GetMapping("/obtenerTodosClientes")
    public List<Cliente> index() {
        log.info("*********** called.. /obtenerTodosClientes");
        return clienteService.findAll();
    }

    @GetMapping("/obtenerClientes/page/{page}")
    public List<Cliente> index(@PathVariable @Min(1) @Max(999999999) Integer page) throws ValidationDataException {
        log.info("*********** called.. /obtenerClientes/page/{page}");
        if (page == null || page <= 0L) {
            log.error("Numero de pagina no valido");
            throw new ValidationDataException("Numero de pagina no valido");
        }
        return clienteService.findAll(page);
    }

    @GetMapping("/consultarCliente/{id}")
    public Cliente show(@PathVariable @Min(1) @Max(999999999) Long id) throws DataAccessException, ValidationDataException {
        if (id == null || id <= 0L) {
            log.error("Identificador no valido");
            throw new ValidationDataException("Identificador no valido");
        }
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

    @PostMapping("/crearCliente")
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

    @PutMapping("/actualizarCliente/{id}")
    public Cliente update(@Valid @RequestBody Cliente cliente, BindingResult result, @PathVariable @Min(1) @Max(999999999) Long id) throws DataAccessException, ValidationDataException {
        if (id == null || id <= 0L) {
            log.error("Identificador no valido");
            throw new ValidationDataException("Identificador no valido");
        }
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
            if (clienteActual != null) {
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

    @PostMapping("/crearFactura/cliente/{id}")
    public void crearFactura(@PathVariable @Min(1) @Max(999999999) Long id,
                                @Valid @RequestBody Factura factura, BindingResult result) throws DataAccessException, ValidationDataException {
        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .collect(Collectors.toList());
            String commaSeparatedString = String.join(" , ", errors);
            throw new ValidationDataException(commaSeparatedString);
        }

        boolean existeCliente = clienteService.isPresenteIdCliente(id);
        if (!existeCliente) {
            String msg = "Error al consultar el cliente con el id_cliente: " + id + ", No existe por favor revisar";
            throw new ValidationDataException(msg);
        }

        try {
            clienteService.saveFactura(factura, id);
        } catch (DataAccessException e) {
            String msg = "Error al realizar el insert en la base de datos una factura asociada a el id_cliente: " + id;
            throw new ValidationDataException(msg, e);
        }
    }

    @GetMapping("/facturas/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Factura findFacturaById(@PathVariable Long id) {
        return clienteService.findFacturaById(id);
    }

    @DeleteMapping("/facturas/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFacturaById(@PathVariable Long id) {
        clienteService.deleteFacturaById(id);
    }
}
