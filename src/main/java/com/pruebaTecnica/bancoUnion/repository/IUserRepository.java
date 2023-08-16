package com.pruebaTecnica.bancoUnion.repository;

import com.pruebaTecnica.bancoUnion.models.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<UserEntity, Long> {


    public UserEntity findByUsername(String username);

}
