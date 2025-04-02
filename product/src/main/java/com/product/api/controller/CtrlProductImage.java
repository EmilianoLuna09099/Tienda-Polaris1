package com.product.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.api.dto.in.DtoProductImageIn;
import com.product.api.service.SvcProductImage;
import com.product.common.dto.ApiResponse;
import com.product.exception.ApiException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/product-image")
@Tag(name = "Imagenes", description = "Administración de imagenes")
public class CtrlProductImage {
	
	@Autowired
    SvcProductImage svc;
	
	
	/*
	 * Endopoint post que crea un prodcutoImagen
	 * @param DtoCategoryIn in, el productoImagen a registrar
	 * */
	@Operation(summary = "Crear Imagenes-productos", description = "Registra una nueva imagen")
    @PostMapping
    public ResponseEntity<ApiResponse> createProductImage(@Valid @RequestBody DtoProductImageIn in, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
        	throw new ApiException(HttpStatus.BAD_REQUEST, bindingResult.getFieldError().getDefaultMessage());

        return svc.uploadProductImage(in);
    }
    
    /*
	 * Endopoint match que actualiza el status a 0
	 * @param Integer id, el prodcutoImagen a actualizar
	 * */
	@Operation(summary = "Borrar Imagenes-productos", description = "Elimina una imagen")

    @DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> disableProduct(@PathVariable Integer id) {
		return svc.deleteProductImage(id);
	}
	
    /*
	 * Endopoint match que actualiza el status a 1
	 * @param Integer id, el prodcutoImagen a actualizar
	 * */
	@Operation(summary = "Habilitar Imagenes-productos", description = "Regresa una imagen a status 1")
    @PatchMapping("/{id}")
	public ResponseEntity<ApiResponse> enableProduct(@PathVariable Integer id) {
		return svc.enableProductImage(id);
	}
    
}
