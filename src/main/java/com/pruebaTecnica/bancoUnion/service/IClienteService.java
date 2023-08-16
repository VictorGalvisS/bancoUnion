package com.pruebaTecnica.bancoUnion.service;

import com.pruebaTecnica.bancoUnion.models.dto.Cliente;
import com.pruebaTecnica.bancoUnion.models.dto.Factura;

import java.util.List;

public interface IClienteService {


    List<Cliente> findAll(Integer page);

    List<Cliente> findAll();

    Cliente save(Cliente cliente);

    Cliente findCliente(Long id);

    boolean delete(Long id);

    Factura findFacturaById(Long id);

    void deleteFacturaById(Long id);
}
