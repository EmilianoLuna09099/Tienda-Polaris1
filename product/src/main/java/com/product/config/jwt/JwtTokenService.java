
package com.product.config.jwt;

import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtTokenService {
    
    // Generar una clave segura
    private final Key SECRET_KEY = Keys.hmacShaKeyFor(
        "ContrasenaSuperSeguraContrasenaSuperSeguraContrasenaSuperSeguraContrasenaSuperSeguraContrasenaSuperSegura".getBytes()
    );

    /**
     * Genera un token JWT con usuario, rol y permisos.
     */
    public String generateToken(String username, String roles) {
        return Jwts.builder()
                .setSubject(username)
                .claim("roles", roles)
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .setIssuedAt(new Date())
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Valida si un token es correcto.
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Obtiene el rol desde un token.
     */
    public String getRoleFromToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.get("roles", String.class);
    }

    /**
     * Obtiene el usuario desde un token.
     */
    public String getUsernameFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
    
    /**
     * Obtiene los permisos desde un token.
     */
    public List<String> getPermisosFromToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
        
        List<String> permisos = claims.get("permisos", List.class);
        return permisos != null ? permisos : List.of(); // Evitar null
    }

  
}