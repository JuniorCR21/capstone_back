package com.api.fmc.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.fmc.models.entity.TipoAtencion;
import com.api.fmc.models.repository.ITipoAtencionDao;
import com.api.fmc.services.ITipoAtencionService;

@Service
public class TipoAtencionServiceImpl implements ITipoAtencionService{

	@Autowired
	private ITipoAtencionDao tipoAtencionDao;
	
	@Transactional(readOnly = true)
	@Override
	public List<TipoAtencion> findAll() {
		return tipoAtencionDao.findAll();
	}

	@Transactional
	@Override
	public TipoAtencion findById(Long id) {
		return tipoAtencionDao.findById(id).orElse(null);
	}

	@Transactional
	@Override
	public TipoAtencion save(TipoAtencion body) {
		return tipoAtencionDao.save(body);
	}

	@Transactional
	@Override
	public void deleteById(Long id) {
		tipoAtencionDao.deleteById(id);
	}

}
