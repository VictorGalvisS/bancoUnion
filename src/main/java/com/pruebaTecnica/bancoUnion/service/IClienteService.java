package com.pruebaTecnica.bancoUnion.service;

import com.pruebaTecnica.bancoUnion.models.dto.Cliente;

import java.util.List;

public interface IClienteService {


    List<Cliente> findAll(Integer page);

    List<Cliente> findAll();

    Cliente save(Cliente cliente);

    Cliente findCliente(Long id);

    boolean delete(Long id);
}
