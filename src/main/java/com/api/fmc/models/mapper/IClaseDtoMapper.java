package com.api.fmc.models.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.api.fmc.models.entity.Cita;
import com.api.fmc.models.entity.Perfil;
import com.api.fmc.models.entity.response.CitaResponse;
import com.api.fmc.models.entity.response.PerfilResponse;

@Mapper
public interface IClaseDtoMapper {

	public PerfilResponse asPerfilDto(Perfil src);
	public List<PerfilResponse> asPerfilesDto(List<Perfil> src);
	public List<CitaResponse> asCitasDto(List<Cita> src);
	public CitaResponse asCitaDto(Cita src);
}
