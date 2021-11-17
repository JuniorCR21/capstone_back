package com.api.fmc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.fmc.services.ITipoAtencionService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/atencion")
public class TipoAtencionController {

	@Autowired
	private ITipoAtencionService tipoAtencionService;
	
	@GetMapping("/")
	public ResponseEntity<?> listarTipoAtencion(){
		return new ResponseEntity<>(tipoAtencionService.findAll(), HttpStatus.OK);
	}
}
