package com.product.config.jwt;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component

public class JwtUtil {
	
	 private final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(
		        "ContrasenaSuperSeguraContrasenaSuperSeguraContrasenaSuperSeguraContrasenaSuperSeguraContrasenaSuperSegura".getBytes()
		    );

		    public Claims extractClaims(String token) {
		        return Jwts.parserBuilder()
		                .setSigningKey(SECRET_KEY)
		                .build()
		                .parseClaimsJws(token)
		                .getBody();
		    }

	    public String extractUsername(String token) {
	        return extractClaim(token, Claims::getSubject);
	    }

		@SuppressWarnings("unchecked")
		public List<HashMap<String, String>> extractPermisos(String token) {
	         return extractClaims(token).get("roles", List.class);
	    }

	    public boolean isTokenValid(String token, String username) {
	        return extractUsername(token).equals(username) && !isTokenExpired(token);
	    }

	    private boolean isTokenExpired(String token) {
	        return extractClaim(token, Claims::getExpiration).before(new Date());
	    }

	    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
	        return claimsResolver.apply(extractClaims(token));
	    }
	    

	    /*public String extractRoles(String token) {
	        return extractClaims(token).get("roles", String.class);
	    }*/
	    
	    public String extractRoles(String token) {
	        Claims claims = extractClaims(token);
	        // Verifica primero con "roles", luego con "authorities", luego con "role"
	        if (claims.get("roles") != null) {
	            return claims.get("roles", String.class);
	        } else if (claims.get("authorities") != null) {
	            return claims.get("authorities", String.class);
	        } else if (claims.get("role") != null) {
	            return claims.get("role", String.class);
	        }
	        return null;
	    }
	
}
