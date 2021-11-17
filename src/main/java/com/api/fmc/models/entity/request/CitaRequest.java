package com.api.fmc.models.entity.request;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CitaRequest {

	private String nombre;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fecha;
	private String comentario;
	private Long id_perfil;
	private Long id_tipousuario;
	private Long id_tipoatencion;
	private String prueba_covid;
	private String responsable;
}
