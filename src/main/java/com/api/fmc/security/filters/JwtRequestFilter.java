package com.api.fmc.security.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.api.fmc.models.entity.Usuario;
import com.api.fmc.models.repository.IUsuarioDao;
import com.api.fmc.security.services.JpaUserDetailsService;
import com.api.fmc.security.utils.JwtUtil;


@Component
public class JwtRequestFilter extends OncePerRequestFilter{
	
	@Autowired
	private JpaUserDetailsService userDetailsService;
	
	@Autowired
	private IUsuarioDao usuarioDao;
	
	@Autowired
	private JwtUtil jwtUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		final String authorizationHeader = request.getHeader("Authorization");
		
		String username = null;
		Usuario user = new Usuario();
		String jwt = null;
		
		if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			jwt = authorizationHeader.substring(7);
			username = jwtUtil.extractUsername(jwt);
			user = usuarioDao.findByDni(Long.parseLong(username));
		}
		
		if(username != null && SecurityContextHolder.getContext().getAuthentication() == null && user.getToken().equals(jwt)) {
			UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
			
			if(jwtUtil.validateToken(jwt, userDetails)) {
				
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		}
		
		filterChain.doFilter(request, response);
	}

}
