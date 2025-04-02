package com.product.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.product.api.dto.in.DtoCategoryIn;
import com.product.api.entity.Category;
import com.product.api.repository.RepoCategory;
import com.product.common.dto.ApiResponse;
import com.product.exception.ApiException;
import com.product.exception.DBAccessException;

@Service
public class SvcCategoryImp implements SvcCategory{
	
	@Autowired
	RepoCategory repo;
	
	/*
	 * Metodo para obtener todas las categorias
	 * @param
	 * */
	@Override
	public ResponseEntity< List<Category>> getCategories(){
		try {
			return new ResponseEntity<>(repo.getCategories(), HttpStatus.OK);
		}catch (DataAccessException e) {
			System.out.println(e.getLocalizedMessage());
			throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR,"Error");
		}
	}
	
	/*
	 * Metodo para obtener las caategorias con status 1
	 * @param
	 * */
	@Override
	public ResponseEntity<List<Category>> getActiveCategory() {
		try {
			return new ResponseEntity<>(repo.getActiveCategories(), HttpStatus.OK);
		}catch (DataAccessException e) {
			throw new DBAccessException(e);
		}
	}
	
	
	/*
	 * Metodo para insertar la categoria a la base de datos
	 * @param category_id, id de la categoria a crear
	 * */
	@Override
	public ResponseEntity<ApiResponse> createCategory(DtoCategoryIn in) {
		try {
			repo.createCategory(in.getCategory(), in.getTag());
			return new ResponseEntity<>(new ApiResponse("La región ha sido registrada"), HttpStatus.CREATED);
		}catch (DataAccessException e) {
			if (e.getLocalizedMessage().contains("ux_region"))
				throw new ApiException(HttpStatus.CONFLICT, "El nombre de la región ya está registrado");
			if (e.getLocalizedMessage().contains("ux_tag"))
				throw new ApiException(HttpStatus.CONFLICT, "El tag de la región ya está registrado");

			throw new DBAccessException(e);
		}
	}
	
	
	/*
	 * Endopoint put que actualiza una categoria
	 * @param DtoCategoryIn in, la categoria a actualizar
	 * @param Integer id, id de la categoria a actuaizar
	 * */
	@Override
	public ResponseEntity<ApiResponse> updateCategory(DtoCategoryIn in, Integer id) {
		try {
			validarCategoryId(id);
			repo.updateCategory(id, in.getCategory(), in.getTag());
			return new ResponseEntity<>(new ApiResponse("La categoria ha sido actualizada"), HttpStatus.OK);
		}catch (DataAccessException e) {
			if (e.getLocalizedMessage().contains("ux_region"))
				throw new ApiException(HttpStatus.CONFLICT, "El nombre de la categoria ya está registrado");
			if (e.getLocalizedMessage().contains("ux_tag"))
				throw new ApiException(HttpStatus.CONFLICT, "El tag de la categoria ya está registrado");

			throw new DBAccessException(e);
		}
	}
	
	
	private void validarCategoryId(Integer id) {
		try {
			if(repo.getCategory(id) == null) {
				throw new ApiException(HttpStatus.NOT_FOUND, "El id de la categoria no existe");
			}
		}catch (DataAccessException e) {
			throw new DBAccessException(e);
		}
	}
	
	
	/*
	 * Metodo para habilitar la categoria de la base de datos
	 * @param category_id, id de la categoria a habilitar
	 * */
	@Override
	public ResponseEntity<ApiResponse> enableCategory(Integer id) {
		try {
			validarCategoryId(id);
			repo.enableCategory(id);
			return new ResponseEntity<>(new ApiResponse("La categoria ha sido activada"), HttpStatus.OK);
		}catch (DataAccessException e) {
			throw new DBAccessException(e);
		}
	}
	
	/*
	 * Metodo para deshabilitar la categoria de la base de datos
	 * @param category_id, id de la categoria a deshabilitar
	 * */
	@Override
	public ResponseEntity<ApiResponse> disableCategory(Integer id) {
		try {
			validarCategoryId(id);
			repo.disableCategory(id);
			return new ResponseEntity<>(new ApiResponse("La categoria ha sido desactivada"), HttpStatus.OK);
		}catch (DataAccessException e) {
			throw new DBAccessException(e);
		}
	}
	
}