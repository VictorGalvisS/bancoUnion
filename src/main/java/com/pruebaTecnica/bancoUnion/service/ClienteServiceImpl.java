package com.pruebaTecnica.bancoUnion.service;

import com.pruebaTecnica.bancoUnion.models.dto.Cliente;
import com.pruebaTecnica.bancoUnion.models.dto.Factura;
import com.pruebaTecnica.bancoUnion.models.entity.ClienteEntity;
import com.pruebaTecnica.bancoUnion.models.entity.FacturaEntity;
import com.pruebaTecnica.bancoUnion.repository.IClienteRepository;
import com.pruebaTecnica.bancoUnion.repository.IFacturaRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Transactional
@Component
public class ClienteServiceImpl implements IClienteService {

    private final IClienteRepository iSearchEventRepository;
    private final IFacturaRepository iFacturaRepository;

    public ClienteServiceImpl(IClienteRepository iSearchEventRepository, IFacturaRepository iFacturaRepository) {
        this.iSearchEventRepository = iSearchEventRepository;
        this.iFacturaRepository = iFacturaRepository;
    }

    @Override
    public List<Cliente> findAll(Integer page) {
        Pageable pageable = PageRequest.of(page, 4);
        Page<ClienteEntity> list = iSearchEventRepository.findAll(pageable);
        if (list != null && !list.isEmpty()) {
            List<ClienteEntity> listPage1 = list.getContent();
            return ClienteEntity.fromListToDomainModel(listPage1);
        }
        return null;
    }

    @Override
    public List<Cliente> findAll() {
        List<ClienteEntity> list = iSearchEventRepository.findAll();
        if (list != null && !list.isEmpty()) {
            return ClienteEntity.fromListToDomainModel(list);
        }
        return null;
    }

    @Override
    public Cliente findCliente(Long id) {
        ClienteEntity entity = null;
        Optional<ClienteEntity> saveClienteEntity = iSearchEventRepository.findById(id);
        if (saveClienteEntity.isPresent() && !saveClienteEntity.isEmpty()) {
            entity = saveClienteEntity.get();
            return entity.toDomainModel();
        }
        return null;
    }

    @Override
    public Cliente save(Cliente cliente) {
        ClienteEntity clienteEntity = ClienteEntity.fromDomainModel(cliente);
        if (clienteEntity == null) {
            return null;
        }
        ClienteEntity saveClienteEntity = iSearchEventRepository.save(clienteEntity);
        if (saveClienteEntity == null) {
            return null;
        }
        return saveClienteEntity.toDomainModel();
    }

    @Override
    public boolean delete(Long id) {
        Optional<ClienteEntity> saveClienteEntity = iSearchEventRepository.findById(id);
        if (saveClienteEntity.isPresent() && !saveClienteEntity.isEmpty()) {
            iSearchEventRepository.delete(saveClienteEntity.get());
            return true;
        }
        return false;
    }

    @Override
    public Factura findFacturaById(Long id) {
         FacturaEntity facturaEntity = iFacturaRepository.findById(id).orElse(null);
         if(facturaEntity != null) {
             return facturaEntity.toDomainModel();
         }
        return null;
    }

    @Override
    public void deleteFacturaById(Long id) {

    }
}
