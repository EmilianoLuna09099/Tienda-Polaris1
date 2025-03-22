package com.product.api.dto.in;


import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;

/*
 * Clase para manejar la construccion y actualizacion de las categorias
 * */
public class DtoCategoryIn {
	
	
	@JsonProperty("category")
	@NotNull(message="La categoria es obligatoria")
	private String category;
		

	@JsonProperty("tag")
	@NotNull(message="El tag es obligatorio")
	private String tag;
	
	/**
	 * Metodo que devuelve la catgoria 
	 * @param
	 * */
	public String getCategory() {
		return category;
	}
	
	/**
	 * Metodo que asigna la catgoria 
	 * @param categoria
	 * */
	public void setCategory(String category) {
		this.category = category;
	}
	
	/**
	 * Metodo que devuelve el tag 
	 * @param
	 * */
	public String getTag() {
		return tag;
	}
	
	/**
	 * Metodo que asigna el tag 
	 * @param tag de la categoria
	 * */
	public void setTag(String tag) {
		this.tag = tag;
	}

}
