package com.api.fmc.models.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "perfil")
public class Perfil implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String nombre;
	@Column
	private String apellidoMaterno;
	@Column
	private String apellidoPaterno;
	@Column(unique = true)
	private String correo;
	@Column
	private String celular;
	@Column(length = 1)
	private String genero;
	@Column
	private String imageUrl;
	@Column
	private int edad;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechaNacimiento;
	
	@OneToOne
	@JoinColumn(name = "direccion_id", nullable = false)
	private Direccion direccion;
	
	@OneToOne
    @JoinColumn(name = "usuario_id", nullable = false)
	private Usuario usuario;
}
