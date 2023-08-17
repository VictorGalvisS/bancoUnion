package com.pruebaTecnica.bancoUnion.repository;


import com.pruebaTecnica.bancoUnion.models.entities.ClienteEntity;
import com.pruebaTecnica.bancoUnion.models.entities.FacturaEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IFacturaRepository extends JpaRepository<FacturaEntity, Long> {

    @Transactional
    @Query("SELECT count(o) FROM FacturaEntity o WHERE o.cliente = :cliente")
    long findCantidadFacturasPorClientes(@Param("cliente") ClienteEntity cliente);
}
