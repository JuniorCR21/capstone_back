package com.api.fmc.security.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.api.fmc.models.entity.Role;
import com.api.fmc.models.entity.Usuario;
import com.api.fmc.models.repository.IUsuarioDao;


@Service
public class JpaUserDetailsService implements UserDetailsService{
	
	@Autowired
	private IUsuarioDao usuarioDao;

	@Override
	public UserDetails loadUserByUsername(String dni) throws UsernameNotFoundException {
		Usuario usuario = usuarioDao.findByDni(Long.parseLong(dni));
		
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		for(Role role: usuario.getRoles()) {
			authorities.add(new SimpleGrantedAuthority(role.getTypeRole()));
		}
		
		return new User(usuario.getDni().toString(), usuario.getPassword(), usuario.getActivo(), true, true,true,authorities);
	}
	
}
