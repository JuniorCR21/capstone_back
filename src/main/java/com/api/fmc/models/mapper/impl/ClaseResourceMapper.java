package com.api.fmc.models.mapper.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.api.fmc.models.entity.Cita;
import com.api.fmc.models.entity.request.CitaRequest;
import com.api.fmc.models.mapper.IClaseResourceMapper;
import com.api.fmc.models.repository.IPerfilDao;
import com.api.fmc.models.repository.ITipoAtencionDao;
import com.api.fmc.models.repository.ITipoUsuarioDao;

@Component
public class ClaseResourceMapper implements IClaseResourceMapper{
	
	@Autowired
	private ITipoAtencionDao atencionDao;
	
	@Autowired
	private ITipoUsuarioDao usuarioDao;
	
	@Autowired
	private IPerfilDao perfilDao;

	@Override
	public Cita asCitaResource(CitaRequest src) {
		if(src == null) {
			return new Cita();
		}
		Cita cita = new Cita();
		cita.setNombre(src.getNombre());
		cita.setFecha(src.getFecha());
		cita.setResponsable(src.getResponsable());
		cita.setComentario(src.getComentario());
		cita.setPerfil(perfilDao.getById(src.getId_perfil()));
		cita.setTipoUsuario(usuarioDao.getById(src.getId_tipousuario()));
		cita.setTipoAtencion(atencionDao.getById(src.getId_tipoatencion()));
		return cita;
	}

}
