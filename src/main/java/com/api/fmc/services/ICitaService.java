package com.api.fmc.services;

import java.util.List;

import com.api.fmc.exceptions.CitaException;
import com.api.fmc.models.entity.Cita;
import com.api.fmc.models.entity.request.CitaRequest;
import com.api.fmc.models.entity.response.CitaResponse;

public interface ICitaService{

	public List<Cita> findAll();
	public List<CitaResponse> findAllByPerfil(Long id);
	public Cita findById(Long id);
	public CitaResponse save (CitaRequest body) throws CitaException;
	public void deleteById(Long id);
}
