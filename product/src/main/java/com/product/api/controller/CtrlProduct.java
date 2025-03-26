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

import com.product.api.dto.in.DtoProductIn;
import com.product.api.dto.out.DtoProductListOut;
import com.product.api.dto.out.DtoProductOut;
import com.product.api.service.SvcProduct;
import com.product.common.dto.ApiResponse;
import com.product.exception.ApiException;

import jakarta.validation.Valid;


/**
 * Clase que define endpoints
 * */
@RestController
@RequestMapping("/product")
public class CtrlProduct {
	
	@Autowired
	SvcProduct svc;
	
	/*
	 * Endopoint get que devuelve todos las productos registrados
	 * @param 
	 * */
	@GetMapping
	public ResponseEntity<List<DtoProductListOut>> getProducts() {
		return svc.getProducts();
	}
	
	/*
	 * Endopoint get que devuelve una categoria especifica
	 * @param id, el ide del prodcuto que se requiere
	 * */
	@GetMapping("/{id}")
	public ResponseEntity<DtoProductOut> getProduct(@PathVariable Integer id) {
		return svc.getProduct(id);
	}
	
	/*
	 * Endopoint post que crea un prodcuto
	 * @param DtoCategoryIn in, el producto a registrar
	 * */
	@PostMapping
	public ResponseEntity<ApiResponse> createProduct(@Valid @RequestBody DtoProductIn in, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			throw new ApiException(HttpStatus.BAD_REQUEST, bindingResult.getFieldError().getDefaultMessage());

		return svc.createProduct(in);
	}
	
	
	/*
	 * Endopoint put que actualiza un producto
	 * @param DtoCategoryIn in, el producto a actualizar
	 * @param Integer id, id de el producto a actuaizar
	 * */
	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse> updateProduct(@PathVariable Integer id, @Valid @RequestBody DtoProductIn in,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			throw new ApiException(HttpStatus.BAD_REQUEST, bindingResult.getFieldError().getDefaultMessage());

		return svc.updateProduct(id, in);
	}
	
	
	/*
	 * Endopoint match que actualiza el status a 1
	 * @param Integer id, el prodcuto a actualizar
	 * */
	@PatchMapping("/{id}/enable")
	public ResponseEntity<ApiResponse> enableProduct(@PathVariable Integer id) {
		return svc.enableProduct(id);
	}
	
	/*
	 * Endopoint match que actualiza el status a 0
	 * @param Integer id, el producto a actualizar
	 * */
	@PatchMapping("/{id}/disable")
	public ResponseEntity<ApiResponse> disableProduct(@PathVariable Integer id) {
		return svc.disableProduct(id);
	}
	
}
