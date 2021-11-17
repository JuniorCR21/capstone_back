package com.api.fmc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.fmc.exceptions.CitaException;
import com.api.fmc.models.entity.request.CitaRequest;
import com.api.fmc.services.ICitaService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/citas")
public class CitaController {

	@Autowired
	private ICitaService citaService;
	
	@GetMapping("/{idPerfil}")
	public ResponseEntity<?> listarCitasPorPerfil (@PathVariable("idPerfil") Long id){
		return new ResponseEntity<>(citaService.findAllByPerfil(id), HttpStatus.OK);
	}
	
	@PostMapping("/")
	public ResponseEntity<?> guardarCita (@RequestBody CitaRequest request){
		try {
			return new ResponseEntity<>(citaService.save(request), HttpStatus.CREATED);
		}catch (CitaException ex) {
			return ResponseEntity.badRequest().body(ex.getMessage());
		}
		
	}
}
