package com.api.fmc.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.fmc.models.entity.TipoUsuario;
import com.api.fmc.models.repository.ITipoUsuarioDao;
import com.api.fmc.services.ITipoUsuarioService;

@Service
public class TipoUsuarioService implements ITipoUsuarioService{

	@Autowired
	private ITipoUsuarioDao tipoUsuarioDao;
	
	@Transactional(readOnly = true)
	@Override
	public List<TipoUsuario> findAll() {
		return tipoUsuarioDao.findAll();
	}

	@Transactional(readOnly = true)
	@Override
	public TipoUsuario findById(Long id) {
		return tipoUsuarioDao.findById(id).orElse(null);
	}

	@Transactional
	@Override
	public TipoUsuario save(TipoUsuario body) {
		return tipoUsuarioDao.save(body);
	}

	@Transactional
	@Override
	public void deleteById(Long id) {
		tipoUsuarioDao.deleteById(id);
	}

}
