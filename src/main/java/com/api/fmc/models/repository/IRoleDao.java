package com.api.fmc.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.fmc.models.entity.Role;

@Repository
public interface IRoleDao extends JpaRepository<Role, Long>{

}
