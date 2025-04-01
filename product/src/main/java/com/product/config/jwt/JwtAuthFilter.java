package com.product.config.jwt;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthFilter extends  OncePerRequestFilter{
	
	 private final JwtUtil jwtUtil;

	    public JwtAuthFilter(JwtUtil jwtUtil) {
	        this.jwtUtil = jwtUtil;
	    }

	    @Override
	    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
	            throws ServletException, IOException {
	        
	        String authHeader = request.getHeader("Authorization");
	        
	        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
	            chain.doFilter(request, response);
	            return;
	        }

	        String token = authHeader.substring(7);
	        String username = jwtUtil.extractUsername(token);
	        String role = jwtUtil.extractRoles(token);
	        //List<HashMap<String, String>> permisos = jwtUtil.extractPermisos(token);
	        
	       // List<String> permisosList = permisos.stream().map(i -> i.get("authority")).toList();
	        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
	        	List<SimpleGrantedAuthority> authorities = extractAuthoritiesFromRoles(role);
	        	UserDetails userDetails = User.withUsername(username)
	            		.password("")
	            		.authorities(authorities)
	            		//.authorities(permisosList.toArray(new String[0]))
	                    .build();

	            UsernamePasswordAuthenticationToken authToken = 
	                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
	            
	            SecurityContextHolder.getContext().setAuthentication(authToken);
	        }

	        chain.doFilter(request, response);
	    }
	    
	    private List<SimpleGrantedAuthority> extractAuthoritiesFromRoles(String roles) {
	        if (roles == null || roles.trim().isEmpty()) {
	            return List.of(new SimpleGrantedAuthority("CUSTOMER"));
	        }
	        
	        return Arrays.stream(roles.split(","))
	                .map(String::trim)
	                .filter(role -> !role.isEmpty())
	                .map(role -> role.replace("ROLE_", "")) // Eliminar ROLE_ si existe
	                .map(SimpleGrantedAuthority::new)
	                .collect(Collectors.toList());
	    }
	
}
