package com.api.fmc.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.api.fmc.models.entity.response.UsuarioRest;

@Service
public class UsuarioRestService {

	@Autowired
	RestTemplate restTemplate;
	
	public UsuarioRest getUsuario(Long dni){
		UsuarioRest user = restTemplate.getForObject("https://api.apis.net.pe/v1/dni?numero="+dni, 
						UsuarioRest.class);
		return user;
	}
}
