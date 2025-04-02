package com.product.api.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.api.dto.in.DtoCategoryIn;
import com.product.api.entity.Category;
import com.product.api.service.SvcCategory;
import com.product.common.dto.ApiResponse;
import com.product.exception.ApiException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/category")
@Tag(name = "Category", description = "Administración de category")

/**
 * Clase que define endpoints
 * */
public class CtrlCategory {
	
	@Autowired
	SvcCategory svc;
	
	/*
	 * Endopoint get que devuelve todas las categorias registradas
	 * @param 
	 * */
	@Operation(summary = "Consultar categorias", description = "Retorna todas las categorias registradas en el sistema")

	@GetMapping
	public ResponseEntity< List<Category>> getCategorias() {
		return svc.getCategories();
	}
	
	/*
	 * Endopoint get que devuelve todas las categorias con staturs 1
	 * @param 
	 * */
	@Operation(summary = "Consultar categorias activas", description = "Retorna todas las categorias con status 1 en el sistema")
	@GetMapping("/active")
	public ResponseEntity<List<Category>> getActiveCategories(){
		return svc.getActiveCategory();
	}
	
	/*
	 * Endopoint post que crea una categoria
	 * @param DtoCategoryIn in, la categoria a registrar
	 * */
	@Operation(summary = "Crear categorias", description = "Registra una nueva categoria")
	@PostMapping()
	public ResponseEntity<ApiResponse> createCategory(@Valid @RequestBody DtoCategoryIn in, BindingResult bindingResult ){
		if (bindingResult.hasErrors())
			throw new ApiException(HttpStatus.BAD_REQUEST, bindingResult.getFieldError().getDefaultMessage());
		
		return svc.createCategory(in);
	}

	
	/*
	 * Endopoint put que actualiza una categoria
	 * @param DtoCategoryIn in, la categoria a actualizar
	 * @param Integer id, id de la categoria a actuaizar
	 * */
	@Operation(summary = "Actualizar categorias", description = "Actualiza la categoria en el sistema")
	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse> updateCategory(@PathVariable("id") Integer id,@Valid @RequestBody DtoCategoryIn in, BindingResult bindingResult){
		if(bindingResult.hasErrors())
			throw new ApiException(HttpStatus.BAD_REQUEST,bindingResult.getAllErrors().get(0).getDefaultMessage());

		return svc.updateCategory(in,id);
	}
	
	
	/*
	 * Endopoint match que actualiza el status a 1
	 * @param Integer id, la categoria a actualizar
	 * */
	@Operation(summary = "Habilitar categorias", description = "Cambia la categoria a status 1 en el sistema")
	@PatchMapping("/{id}/enable")
	public ResponseEntity<ApiResponse> enableCategory(@PathVariable Integer id){
		return svc.enableCategory(id);
	}
	
	
	/*
	 * Endopoint match que actualiza el status a 0
	 * @param Integer id, la categoria a actualizar
	 * */
	@Operation(summary = "Deshabilitar categorias", description = "Cambia la categoria a status 0 en el sistema")
	@PatchMapping("/{id}/disable")
	public ResponseEntity<ApiResponse> disableCategory(@PathVariable Integer id){
		return svc.disableCategory(id);
	}
	
	
}
