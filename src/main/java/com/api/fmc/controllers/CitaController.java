package com.api.fmc.controllers;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.api.fmc.exceptions.CitaException;
import com.api.fmc.models.entity.request.CitaRequest;
import com.api.fmc.services.ICitaService;
import com.api.fmc.utils.Constants;

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
	
	@GetMapping("/past/{idPerfil}")
	public ResponseEntity<?> listarCitasPasadasPorPerfil (@PathVariable("idPerfil") Long id){
		return new ResponseEntity<>(citaService.findAllPastByPerfil(id), HttpStatus.OK);
	}
	
	@PostMapping("/")
	public ResponseEntity<?> guardarCita (@RequestBody CitaRequest request){
		try {
			return new ResponseEntity<>(citaService.save(request), HttpStatus.CREATED);
		}catch (CitaException ex) {
			return ResponseEntity.badRequest().body(ex.getMessage());
		}
		
	}
	
	@GetMapping("/cancel/{idCita}")
	public ResponseEntity<?> cancelarCita(@PathVariable("idCita")Long id){
		try {
			return new ResponseEntity<>(citaService.cancelCita(id), HttpStatus.OK);
		}catch(CitaException ex) {
			return ResponseEntity.badRequest().body(ex.getMessage());
		}
	}
	
	@GetMapping("/attend/{idPerfil}")
	public ResponseEntity<?> listarCitasAtendidasPorPerfil (@PathVariable("idPerfil") Long id){
		return new ResponseEntity<>(citaService.attendCitaPerfil(id), HttpStatus.OK);
	}
	
	@PostMapping(value = "/attend/{idCita}")
	public ResponseEntity<?> atenderCita(@RequestParam("file") MultipartFile multipart, @PathVariable("idCita") Long id) throws IOException{
		return new ResponseEntity<>(citaService.attendCita(multipart, id),HttpStatus.OK);
	}
}
