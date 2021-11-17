package com.api.fmc.models.mapper;

import org.mapstruct.Mapper;

import com.api.fmc.models.entity.Cita;
import com.api.fmc.models.entity.request.CitaRequest;

@Mapper
public interface IClaseResourceMapper {

	public Cita asCitaResource(CitaRequest src);
}
