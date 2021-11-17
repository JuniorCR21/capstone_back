package com.api.fmc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.userdetails.UserDetails;
import com.api.fmc.models.entity.request.AuthenticationRequest;
import com.api.fmc.security.services.JpaUserDetailsService;
import com.api.fmc.security.utils.JwtUtil;
import com.api.fmc.services.IPerfilService;
import com.api.fmc.services.IUsuarioService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/user")
public class UsuarioController {
	
	@Autowired
	private JpaUserDetailsService userDetailsService;
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@Autowired
	private IPerfilService perfilService;
	
	@Autowired
	private JwtUtil jwtTokenUtil;

	@PostMapping("/login")
	public ResponseEntity<?> createAuthenticationToken (@RequestBody AuthenticationRequest authenticationRequest) throws Exception{
		
		if(usuarioService.findByDniAndPassword(authenticationRequest.getDni(), authenticationRequest.getPassword()) == null) {
			 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getDni().toString());
		final String jwt = jwtTokenUtil.generateToken(userDetails);
		return ResponseEntity.ok(perfilService.findByIdUsuario(usuarioService.saveToken(authenticationRequest, jwt).getId()));
	}
	
	@GetMapping("/logout")
	public ResponseEntity<?> logoutAuthentication (@RequestParam("dni") Long dni) {
		usuarioService.deleteToken(dni);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
