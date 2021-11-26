package com.api.fmc.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.api.fmc.exceptions.CitaException;
import com.api.fmc.models.entity.Cita;
import com.api.fmc.models.entity.request.CitaRequest;
import com.api.fmc.models.entity.response.CitaResponse;

public interface ICitaService{

	public List<Cita> findAll();
	public List<CitaResponse> findAllByPerfil(Long id);
	public List<CitaResponse> findAllPastByPerfil(Long id);
	public Cita findById(Long id);
	public CitaResponse save (CitaRequest body) throws CitaException;
	public CitaResponse cancelCita (Long id) throws CitaException;
	public CitaResponse attendCita (MultipartFile multipart, Long id);
	public List<?> attendCitaPerfil (Long id);
	public void deleteById(Long id);
}
