package com.pruebaTecnica.bancoUnion.service;

import com.pruebaTecnica.bancoUnion.models.dto.Cliente;
import com.pruebaTecnica.bancoUnion.models.dto.Factura;

import java.util.List;

public interface IClienteService {


    boolean isPresenteIdCliente(Long idCliente);

    List<Cliente> findAll(Integer page);

    List<Cliente> findAll();

    Cliente save(Cliente cliente);

    void saveFactura(Factura factura, Long idCliente);

    Cliente findCliente(Long id);

    boolean delete(Long id);

    Factura findFacturaById(Long id);

    boolean deleteFacturaById(Long id);
}
