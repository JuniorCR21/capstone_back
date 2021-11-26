package com.api.fmc.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.api.fmc.models.entity.Cita;

@Repository
public interface ICitaDao extends JpaRepository<Cita, Long>{

	@Query(value="SELECT * FROM cita u WHERE u.perfil_id=?1 and u.status LIKE 'REGISTRADA'", nativeQuery = true)
	public List<Cita> findAllByPerfil(Long id);
	
	@Query(value="SELECT * FROM cita u WHERE u.perfil_id=?1 and (u.status LIKE 'CANCELADA' or u.status LIKE 'ATENDIDA')", nativeQuery = true)
	public List<Cita> findAllPastByPerfil(Long id);
	
	@Query(value="SELECT * FROM cita u WHERE u.perfil_id=?1 and u.status LIKE 'ATENDIDA'", nativeQuery = true)
	public List<Cita> findAllAttendByPerfil(Long id);
}
