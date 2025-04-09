package com.product.api.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.product.config.jwt.JwtTokenService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	private final JwtTokenService tokenService;

    public AuthController(JwtTokenService tokenService) {
        this.tokenService = tokenService;
    }

    /*@PostMapping("/generate-token")
    public String generateToken(@RequestParam String username, @RequestParam String role) {
        return tokenService.generateToken(username, role);
    }*/
    
    @PostMapping("/generate-token")
    public String generateToken(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String role = request.get("roles");
        return tokenService.generateToken(username, role);
    }
	
    @GetMapping("/validate-token")
    public boolean validateToken(@RequestParam String token) {
        return tokenService.validateToken(token);
    }
    
    @GetMapping("/get-username")
    public String getUsername(@RequestParam String token) {
        return tokenService.getUsernameFromToken(token);
    }

    // 🔍 Obtener Rol desde el Token
    @GetMapping("/get-roles")
    public String getRole(@RequestParam String token) {
        return tokenService.getRoleFromToken(token);
    }
}
