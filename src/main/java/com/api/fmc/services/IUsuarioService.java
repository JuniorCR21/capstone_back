package com.api.fmc.services;

import com.api.fmc.exceptions.UsuarioException;
import com.api.fmc.models.entity.Usuario;
import com.api.fmc.models.entity.request.AuthenticationRequest;

public interface IUsuarioService{

	public Usuario save(Usuario body) throws UsuarioException;
	public Usuario saveToken (AuthenticationRequest request, String token);
	public Usuario findByDniAndPassword(Long dni, String password);
	public void deleteToken (Long dni);
}
