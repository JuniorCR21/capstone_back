package com.api.fmc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.fmc.exceptions.UsuarioException;
import com.api.fmc.models.entity.Perfil;
import com.api.fmc.models.entity.response.PerfilResponse;
import com.api.fmc.services.IPerfilService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/profile")
public class PerfilController {

	@Autowired
	private IPerfilService perfilService;
	
	@PostMapping("/")
	public ResponseEntity<PerfilResponse> saveProfile (@RequestBody Perfil perfil) throws UsuarioException{
		return new ResponseEntity<>(perfilService.save(perfil), HttpStatus.CREATED);
	}
}
