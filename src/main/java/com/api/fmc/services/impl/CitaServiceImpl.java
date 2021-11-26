package com.api.fmc.services.impl;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.api.fmc.exceptions.CitaException;
import com.api.fmc.models.entity.Cita;
import com.api.fmc.models.entity.Perfil;
import com.api.fmc.models.entity.TipoAtencion;
import com.api.fmc.models.entity.TipoUsuario;
import com.api.fmc.models.entity.request.CitaRequest;
import com.api.fmc.models.entity.response.CitaResponse;
import com.api.fmc.models.mapper.IClaseDtoMapper;
import com.api.fmc.models.repository.ICitaDao;
import com.api.fmc.models.repository.IPerfilDao;
import com.api.fmc.models.repository.ITipoAtencionDao;
import com.api.fmc.models.repository.ITipoUsuarioDao;
import com.api.fmc.services.ICitaService;
import com.api.fmc.utils.Constants;

@Service
public class CitaServiceImpl implements ICitaService{

	@Autowired
	private ICitaDao citaDao;
	
	@Autowired
	private IPerfilDao perfilDao;
	
	@Autowired
	private ITipoAtencionDao atencionDao;
	
	@Autowired
	private ITipoUsuarioDao usuarioDao;
	
	@Autowired
	private IClaseDtoMapper dtoMapper;
	
	@Autowired
	private FTPClientService ftpCliente;
	
	private static final String FOLDER = "documents/";
	
	@Transactional(readOnly = true)
	@Override
	public List<Cita> findAll() {
		return citaDao.findAll();
	}

	@Override
	public Cita findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	@Override
	public CitaResponse save(CitaRequest body) throws CitaException{
		if(!Constants.verificarFechaCita(body.getFecha())) {
			throw new CitaException(Constants.citaErrorFecha);
		}
		Cita cita = new Cita();
		Optional<Perfil> perfil = perfilDao.findById(body.getId_perfil());
		Optional<TipoAtencion> tipoAtencion = atencionDao.findById(body.getId_tipoatencion());
		Optional<TipoUsuario> tipoUsuario = usuarioDao.findById(body.getId_tipousuario());
		cita.setComentario(body.getComentario());
		cita.setFecha(body.getFecha());
		cita.setNombre(body.getNombre());
		cita.setPerfil(perfil.get());
		cita.setTipoAtencion(tipoAtencion.get());
		cita.setTipoUsuario(tipoUsuario.get());
		cita.setStatus("REGISTRADA");
		cita.setPruebaCovid(body.getPrueba_covid());
		cita.setResponsable(body.getResponsable());
		return dtoMapper.asCitaDto(citaDao.save(cita));
	}

	@Override
	public void deleteById(Long id) {
		citaDao.deleteById(id);
	}

	@Transactional(readOnly = true)
	@Override
	public List<CitaResponse> findAllByPerfil(Long id) {
		List<Cita> citas= citaDao.findAllByPerfil(id);
		if(citas.isEmpty()) {
			return Collections.emptyList();
		}
		return dtoMapper.asCitasDto(citas);
	}

	@Transactional
	@Override
	public CitaResponse cancelCita(Long id) throws CitaException {
		Cita cita = citaDao.findById(id).orElse(null);
		if(cita == null) {
			throw new CitaException(Constants.CITA_NOT_FOUND);
		}
		cita.setStatus("CANCELADA");
		return dtoMapper.asCitaDto(citaDao.save(cita));
	}
	
	@Transactional
	@Override
	public List<CitaResponse> findAllPastByPerfil(Long id) {
		List<Cita> citas= citaDao.findAllPastByPerfil(id);
		if(citas.isEmpty()) {
			return Collections.emptyList();
		}
		return dtoMapper.asCitasDto(citas);
	}

	@Transactional
	@Override
	public CitaResponse attendCita(MultipartFile multipart, Long id) {
		Cita cita = citaDao.findById(id).orElse(null);
		try {
			cita.setDocument(ftpCliente.uploadDocument(multipart, FOLDER + cita.getId() + "-" + cita.getPerfil().getApellidoPaterno() + cita.getPerfil().getApellidoMaterno() + "_" + cita.getFecha().toString()+".pdf"));
			cita.setStatus("ATENDIDA");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dtoMapper.asCitaDto(citaDao.save(cita));
	}

	@Transactional(readOnly = true)
	@Override
	public List<?> attendCitaPerfil(Long id) {
		List<Cita> citas = citaDao.findAllAttendByPerfil(id);
		if(citas.isEmpty()) {
			return Collections.emptyList();
		}
		return dtoMapper.asCitasDto(citas);
	}

}
