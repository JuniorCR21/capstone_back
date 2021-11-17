package com.api.fmc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.fmc.services.ITipoUsuarioService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/tusuario")
public class TipoUsuarioController {

	@Autowired
	private ITipoUsuarioService tipoUsuarioService;
	
	@GetMapping("/")
	public ResponseEntity<?> listarTipoUsuario(){
		return new ResponseEntity<>(tipoUsuarioService.findAll(), HttpStatus.OK);
	}
}
