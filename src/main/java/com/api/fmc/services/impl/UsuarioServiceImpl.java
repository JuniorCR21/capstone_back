package com.api.fmc.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.fmc.exceptions.UsuarioException;
import com.api.fmc.models.entity.Role;
import com.api.fmc.models.entity.Usuario;
import com.api.fmc.models.entity.request.AuthenticationRequest;
import com.api.fmc.models.entity.response.UsuarioRest;
import com.api.fmc.models.repository.IRoleDao;
import com.api.fmc.models.repository.IUsuarioDao;
import com.api.fmc.services.IUsuarioService;

@Service
public class UsuarioServiceImpl implements IUsuarioService{
	
	@Autowired
	private IUsuarioDao usuarioDao;
	
	@Autowired
	private IRoleDao roleDao;
	
	@Autowired
	private UsuarioRestService rest;
	
	@Autowired
	private BCryptPasswordEncoder passEncoder;

	@Transactional
	@Override
	public Usuario save(Usuario body) throws UsuarioException{
		if(body == null) {
			throw new UsuarioException("Error al registrar el usuario - Datos Nulos");
		}
		UsuarioRest userRest = rest.getUsuario(body.getDni());
		
		if(Long.parseLong(userRest.getNumeroDocumento()) != body.getDni()) {
			throw new UsuarioException("DNI INVALIDO - No se encontró usuario");
		}
		body.setActivo(true);
		body.setPassword(passEncoder.encode(body.getPassword()));
		Usuario user = usuarioDao.save(body);
		
		List<Role> roles= new ArrayList<>();
		Role role = new Role();
		role.setTypeRole("ROLE_USER");
		role.setUsuario(user);
		roles.add(role);
		user.setRoles(roleDao.saveAll(roles));
		return usuarioDao.save(user);
	}


	@Transactional
	@Override
	public Usuario saveToken(AuthenticationRequest request, String token) {
		Usuario usuario = usuarioDao.findByDni(request.getDni());
		usuario.setToken(token);
		return usuarioDao.save(usuario);
	}
	
	@Transactional(readOnly = true)
	@Override
	public Usuario findByDniAndPassword(Long dni, String password) {
		Usuario user = usuarioDao.findByDni(dni);
		if(user == null || !passEncoder.matches(password, user.getPassword())) {
			return null;
		}
		return user;
	}

	@Override
	public void deleteToken(Long dni) {
		Usuario user = usuarioDao.findByDni(dni);
		user.setToken("");
		usuarioDao.save(user);
	}

}
