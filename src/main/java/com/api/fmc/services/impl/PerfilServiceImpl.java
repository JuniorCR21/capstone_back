package com.api.fmc.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.fmc.exceptions.UsuarioException;
import com.api.fmc.models.entity.Perfil;
import com.api.fmc.models.entity.response.PerfilResponse;
import com.api.fmc.models.mapper.IClaseDtoMapper;
import com.api.fmc.models.repository.IDireccionDao;
import com.api.fmc.models.repository.IPerfilDao;
import com.api.fmc.services.IPerfilService;
import com.api.fmc.services.IUsuarioService;
import com.api.fmc.utils.Constants;

@Service
public class PerfilServiceImpl implements IPerfilService{

	@Autowired
	private IPerfilDao perfilDao;
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@Autowired
	private IClaseDtoMapper dtoMapper;
	
	@Autowired
	private IDireccionDao direccionDao;
	
	@Transactional(readOnly = true)
	@Override
	public List<PerfilResponse> findAll() {
		return dtoMapper.asPerfilesDto(perfilDao.findAll());
	}

	@Transactional(readOnly = true)
	@Override
	public PerfilResponse findById(Long id) {
		return dtoMapper.asPerfilDto(perfilDao.getById(id));
	}

	@Transactional
	@Override
	public PerfilResponse save(Perfil body) throws UsuarioException {
		if(body == null || body.getDireccion() == null) {
			return new PerfilResponse();
		}
		body.setEdad(Constants.obtenerEdad(body.getFechaNacimiento()));
		try {
			body.setUsuario(usuarioService.save(body.getUsuario()));
		} catch (UsuarioException ex) {
			throw new UsuarioException(ex.getMessage());
		}
		body.setDireccion(direccionDao.save(body.getDireccion()));
		return dtoMapper.asPerfilDto(perfilDao.save(body));
	}

	@Transactional
	@Override
	public void deleteById(Long id) {
		perfilDao.deleteById(id);
	}
	
	@Transactional
	@Override
	public PerfilResponse findByIdUsuario(Long id) {
		Perfil perfil = perfilDao.findUsuarioById(id);
		return dtoMapper.asPerfilDto(perfil);
	}

}
