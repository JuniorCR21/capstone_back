package com.api.fmc.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.fmc.models.entity.Role;
import com.api.fmc.models.repository.IRoleDao;
import com.api.fmc.services.IRoleService;

@Service
public class RoleServiceImpl implements IRoleService{
	
	@Autowired
	private IRoleDao roleDao;

	@Override
	public List<Role> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Role findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Role save(Role body) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		
	}
}
