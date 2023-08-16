package com.pruebaTecnica.bancoUnion.repository;


import com.pruebaTecnica.bancoUnion.models.entity.FacturaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFacturaRepository extends JpaRepository<FacturaEntity, Long> {

}
