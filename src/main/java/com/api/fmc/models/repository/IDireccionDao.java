package com.api.fmc.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.fmc.models.entity.Direccion;

@Repository
public interface IDireccionDao extends JpaRepository<Direccion, Long>{

}
