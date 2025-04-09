package com.product.api.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.product.api.dto.in.DtoCategoryIn;
import com.product.api.entity.Category;
import com.product.common.dto.ApiResponse;

/*
 * Interfaz que define los metodos del servicio
 * */
public interface SvcCategory{
	
	
	/*
	 * Metodo para obtener todas las categorias
	 * @param
	 * */
	public ResponseEntity< List<Category>> getCategories();
	
	/*
	 * Metodo para obtener las caategorias con status 1
	 * @param
	 * */
	public ResponseEntity<List<Category>> getActiveCategory();
	
	/*
	 * Metodo para insertar la categoria a la base de datos
	 * @param category_id, id de la categoria a crear
	 * */
	public ResponseEntity<ApiResponse> createCategory(DtoCategoryIn in);
	
	/*
	 * Endopoint put que actualiza una categoria
	 * @param DtoCategoryIn in, la categoria a actualizar
	 * @param Integer id, id de la categoria a actuaizar
	 * */
	public ResponseEntity<ApiResponse> updateCategory(DtoCategoryIn in, Integer id);
	
	/*
	 * Metodo para habilitar la categoria de la base de datos
	 * @param category_id, id de la categoria a habilitar
	 * */
	public ResponseEntity<ApiResponse> enableCategory(Integer id);
	
	/*
	 * Metodo para deshabilitar la categoria de la base de datos
	 * @param category_id, id de la categoria a deshabilitar
	 * */
	public ResponseEntity<ApiResponse> disableCategory(Integer id);

	
}