package com.api.fmc.security.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtUtil {

	private String SECRET_KEY = "proyectoadmin";
	private Long expiration = 3600L;
	
	public String extractUsername (String token) {
		return extractClaim(token, Claims::getSubject);
	}
	
	public Date extractExpiration (String token) {
		return extractClaim(token, Claims::getExpiration);
	}
	
	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}
	
	private Claims extractAllClaims (String token) {
		return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
	}
	
	private Boolean isTokenExpired (String token) {
		return extractExpiration(token).before(new Date());
	}
	 
	public String generateToken (UserDetails userDetails) {
		Map<String,Object> claims = new HashMap<>();
		return createToken(claims, userDetails.getUsername());
	}
	
	private String createToken (Map<String,Object>claims, String subject) {
		final Date createdDate = new Date();
        final Date expirationDate = calculateExpirationDate(createdDate);
        
		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(createdDate)
				.setExpiration(expirationDate)
				.signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
	}
	
	public Boolean validateToken (String token, UserDetails userDetails) {
		final String username = extractUsername(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}
	
	public String refreshToken (String token) {
		final Date createdDate = new Date();
		final Date expirationDate = calculateExpirationDate(createdDate);
		
		final Claims claims = extractAllClaims(token);
		claims.setIssuedAt(createdDate);
		claims.setExpiration(expirationDate);
		
		return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, SECRET_KEY).compact();
	}
	
	 private Date calculateExpirationDate(Date createdDate) {
	        return new Date(createdDate.getTime() + expiration * 1000);
	 }
	
}
