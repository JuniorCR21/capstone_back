package com.api.fmc.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.api.fmc.models.entity.Perfil;

@Repository
public interface IPerfilDao extends JpaRepository<Perfil, Long>{

	@Query(value = "SELECT * FROM perfil u WHERE u.usuario_id=?1" ,nativeQuery = true)
	public Perfil findUsuarioById(Long id);
}
