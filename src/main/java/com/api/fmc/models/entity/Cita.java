package com.api.fmc.models.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "cita")
public class Cita implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private TipoUsuario tipoUsuario;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private TipoAtencion tipoAtencion;
	
	@Column(name="prueba_covid")
	private String pruebaCovid;
	
	@Column
	private String nombre;
	
	@Column(nullable = true)
	private String responsable;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fecha;
	
	@Column(nullable = true)
	private String comentario;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Perfil perfil;
	
	@Column
	private String status;
	
	@Column
	private String document;

}
