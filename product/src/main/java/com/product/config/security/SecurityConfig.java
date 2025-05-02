package com.product.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.product.config.jwt.JwtAuthFilter;

@Configuration
public class SecurityConfig {
	
	@Autowired
	private JwtAuthFilter jwtFilter;
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http, CorsConfig corsConfig) throws Exception {

	    http.csrf(AbstractHttpConfigurer::disable)
	        .authorizeHttpRequests(
	            auth -> auth
	                .requestMatchers("/error", "/swagger-ui/**", "/v3/api-docs/**", "/actuator/info", "/actuator/health").permitAll()
	                
	                // 🔥 Permitir generación de token sin autenticación
	                .requestMatchers(HttpMethod.POST, "/auth/generate-token").permitAll()
	                
	                // Categorías (Customer solo puede ver activas)
	                .requestMatchers(HttpMethod.GET, "/category/active").hasAnyAuthority("CUSTOMER", "ADMIN")
	                .requestMatchers(HttpMethod.GET, "/category").hasAuthority("ADMIN")
	                
	                // Productos (Customer puede ver listado y detalles)
	                .requestMatchers(HttpMethod.GET, "/product").hasAnyAuthority("CUSTOMER", "ADMIN")
	                .requestMatchers(HttpMethod.GET, "/product/{id}").hasAnyAuthority("CUSTOMER", "ADMIN")
	                
	                // Imágenes de productos (Customer puede ver)
	                .requestMatchers(HttpMethod.GET, "/product-image/**").hasAnyAuthority("CUSTOMER", "ADMIN")
	                
	                // Operaciones de escritura (solo Admin)
	                .requestMatchers(HttpMethod.POST, "/category").hasAuthority("ADMIN")
	                .requestMatchers(HttpMethod.PUT, "/category/{id}").hasAuthority("ADMIN")
	                .requestMatchers(HttpMethod.PATCH, "/category/{id}/enable", "/category/{id}/disable").hasAuthority("ADMIN")
	                .requestMatchers(HttpMethod.POST, "/product").hasAuthority("ADMIN")
	                .requestMatchers(HttpMethod.PUT, "/product/{id}").hasAuthority("ADMIN")
	                .requestMatchers(HttpMethod.PATCH, "/product/{id}/enable", "/product/{id}/disable").hasAuthority("ADMIN")
	                .requestMatchers(HttpMethod.POST, "/product-image").hasAuthority("ADMIN")
	                .requestMatchers(HttpMethod.DELETE, "/product-image/{id}").hasAuthority("ADMIN")
	                .requestMatchers(HttpMethod.PATCH, "/product-image/{id}").hasAuthority("ADMIN")
	        )
	        .cors(cors -> cors.configurationSource(corsConfig))
	        .httpBasic(Customizer.withDefaults())
	        .formLogin(AbstractHttpConfigurer::disable)
	        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
	        .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

	    return http.build();
	}
	
	
}
