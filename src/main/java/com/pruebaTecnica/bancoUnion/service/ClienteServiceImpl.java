package com.pruebaTecnica.bancoUnion.service;

import com.pruebaTecnica.bancoUnion.models.dto.Cliente;
import com.pruebaTecnica.bancoUnion.models.dto.Factura;
import com.pruebaTecnica.bancoUnion.models.entities.ClienteEntity;
import com.pruebaTecnica.bancoUnion.models.entities.FacturaEntity;
import com.pruebaTecnica.bancoUnion.repository.IClienteRepository;
import com.pruebaTecnica.bancoUnion.repository.IFacturaRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class ClienteServiceImpl implements IClienteService {

    private final IClienteRepository iSearchEventRepository;
    private final IFacturaRepository iFacturaRepository;

    public ClienteServiceImpl(IClienteRepository iSearchEventRepository, IFacturaRepository iFacturaRepository) {
        this.iSearchEventRepository = iSearchEventRepository;
        this.iFacturaRepository = iFacturaRepository;
    }

    @Override
    public boolean isPresenteIdCliente(Long idCliente) {
        if(idCliente == null || idCliente<= 0) {
            return false;
        }
        return iSearchEventRepository.existsById(idCliente);
    }


    @Override
    public List<Cliente> findAll(Integer page) {
        Pageable pageable = PageRequest.of(page, 4);
        Page<ClienteEntity> list = iSearchEventRepository.findAll(pageable);
        if (list != null && !list.isEmpty()) {
            List<ClienteEntity> listPage1 = list.getContent();
            return Cliente.fromListToDomainModel(listPage1);
        }
        return null;
    }

    @Override
    public List<Cliente> findAll() {
        List<ClienteEntity> list = iSearchEventRepository.findAll();
        if (list != null && !list.isEmpty()) {
            return Cliente.fromListToDomainModel(list);
        }
        return null;
    }

    @Override
    public Cliente findCliente(Long idCliente) {
        ClienteEntity entity = null;
        Optional<ClienteEntity> saveClienteEntity = iSearchEventRepository.findById(idCliente);
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
    public void saveFactura(Factura factura, Long idCliente) {
        Optional<ClienteEntity> saveClienteEntity = iSearchEventRepository.findById(idCliente);
        if(saveClienteEntity != null && saveClienteEntity.isPresent()) {
            FacturaEntity facturaEntity = FacturaEntity.fromDomainModelWithIdCliente(factura, idCliente);
            iFacturaRepository.save(facturaEntity);
        }
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
        FacturaEntity facturaEntity = obtenerFactura(id);
         if(facturaEntity != null) {
             return facturaEntity.toDomainModel();
         }
        return null;
    }

    @Override
    public boolean deleteFacturaById(Long id) {
        FacturaEntity facturaEntity = obtenerFactura(id);
        if(facturaEntity != null) {
            iFacturaRepository.delete(facturaEntity);
            return true;
        }
        return false;
    }

    private FacturaEntity obtenerFactura(Long id) {
        return iFacturaRepository.findById(id).orElse(null);
    }
}
