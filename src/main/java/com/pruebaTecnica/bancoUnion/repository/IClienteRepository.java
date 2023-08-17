package com.pruebaTecnica.bancoUnion.repository;

import com.pruebaTecnica.bancoUnion.models.entities.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IClienteRepository extends JpaRepository<ClienteEntity, Long> {


}
