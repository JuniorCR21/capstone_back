package com.api.fmc.models.entity.response;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PerfilResponse {

	private Long id;
	private String nombre;
	private String apellido_materno;
	private String apellido_paterno;
	private LocalDate fechaNacimiento;
	private Long dni;
	private String celular;
	private int edad;
	private String genero;
	private String correo;
	private String departamento;
	private String provincia;
	private String distrito;
	private String direccion;
	private String imageUrl;
	private String token;
}
