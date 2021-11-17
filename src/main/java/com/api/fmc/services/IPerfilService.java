package com.api.fmc.services;

import java.util.List;

import com.api.fmc.exceptions.UsuarioException;
import com.api.fmc.models.entity.Perfil;
import com.api.fmc.models.entity.response.PerfilResponse;

public interface IPerfilService{

	public List<PerfilResponse> findAll();
	public PerfilResponse findById(Long id);
	public PerfilResponse findByIdUsuario(Long dni);
	public PerfilResponse save (Perfil body) throws UsuarioException;
	public void deleteById(Long id);
}
