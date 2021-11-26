package com.api.fmc.models.mapper.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;

import com.api.fmc.models.entity.Cita;
import com.api.fmc.models.entity.Perfil;
import com.api.fmc.models.entity.response.CitaResponse;
import com.api.fmc.models.entity.response.PerfilResponse;
import com.api.fmc.models.mapper.IClaseDtoMapper;

@Component
public class ClaseDtoMapperImpl implements IClaseDtoMapper{

	@Override
	public List<PerfilResponse> asPerfilesDto(List<Perfil> src) {
		if(src == null) {
			return Collections.emptyList();
		}
		List<PerfilResponse> list = new ArrayList<>(src.size());
		for(Perfil perfil : src) {
			list.add(asPerfilDto(perfil));
		}
		return list;
	}

	@Override
	public PerfilResponse asPerfilDto(Perfil src) {
		if(src == null) {
			return new PerfilResponse();
		}
		
		PerfilResponse perfil = new PerfilResponse();
		perfil.setId(src.getId());
		perfil.setApellido_materno(src.getApellidoMaterno());
		perfil.setApellido_paterno(src.getApellidoPaterno());
		perfil.setCorreo(src.getCorreo());
		perfil.setDepartamento(src.getDireccion().getDepartamento());
		perfil.setDireccion(src.getDireccion().getDireccion());
		perfil.setDistrito(src.getDireccion().getDistrito());
		perfil.setProvincia(src.getDireccion().getProvincia());
		perfil.setDni(src.getUsuario().getDni());
		perfil.setGenero(src.getGenero());
		perfil.setEdad(src.getEdad());
		perfil.setFechaNacimiento(src.getFechaNacimiento());
		perfil.setNombre(src.getNombre());
		perfil.setCelular(src.getCelular());
		perfil.setImageUrl(src.getImageUrl());
		perfil.setToken(src.getUsuario().getToken());
		return perfil;
	}

	@Override
	public List<CitaResponse> asCitasDto(List<Cita> src) {
		if(src == null) {
			return Collections.emptyList();
		}
		List<CitaResponse> list = new ArrayList<>(src.size());
		for(Cita cita: src) {
			list.add(asCitaDto(cita));
		}
		return list;
	}

	@Override
	public CitaResponse asCitaDto(Cita src) {
		if(src == null) {
			return new CitaResponse();
		}
		CitaResponse cita = new CitaResponse();
		cita.setComentario(src.getComentario());
		cita.setFecha(src.getFecha());
		cita.setId(src.getId());
		cita.setNombre(src.getNombre());
		cita.setPrueba_covid(src.getPruebaCovid());
		cita.setResponsable(src.getResponsable());
		cita.setStatus(src.getStatus());
		cita.setTipo_atencion(src.getTipoAtencion().getNombre());
		cita.setTipo_usuario(src.getTipoUsuario().getNombre());
		cita.setDocument(src.getDocument());
		return cita;
	}

}
