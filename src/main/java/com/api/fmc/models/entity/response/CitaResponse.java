package com.api.fmc.models.entity.response;

import java.time.LocalDate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CitaResponse {

	private Long id;
	private String nombre;
	private LocalDate fecha;
	private String tipo_usuario;
	private String tipo_atencion;
	private String prueba_covid;
	private String responsable;
	private String comentario;
	private String status;
}
