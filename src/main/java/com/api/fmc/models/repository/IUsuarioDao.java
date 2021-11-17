package com.api.fmc.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.fmc.models.entity.Usuario;

@Repository
public interface IUsuarioDao extends JpaRepository<Usuario, Long>{

	public Usuario findByDni(Long dni);
	public Usuario findByDniAndPassword(Long dni, String password);
}
