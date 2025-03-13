package com.product.api.entity;


import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/*
 * Clase Entidad que define las categorias y sus atributos
 * */

@Entity
@Table(name= "category")
public class Category {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	
	@Id
	@JsonProperty("category_id")
	@Column(name = "category_id")
	Integer category_id;
	
	@JsonProperty("category")
	@Column(name = "category")
    String category;
	
	@JsonProperty("tag")
	@Column(name = "tag")
    String tag;
	
	@JsonProperty("status")
	@Column(name = "status")
    Integer status;
	
    public Category() {
    }
    
    /**
     * Constructor de entidad Category
     * @param Category_id, id de la categoria
     * @param category, nombre de la categoria
     * @param tag, tag de la categoria
     * */
    public Category(Integer category_id, String category, String tag) {
    	this.category_id=category_id;
    	this.category=category;
    	this.tag=tag;
    	this.status=1;
    }
    
    /*
     * Metodo toString para visualizar una categoria
     * */
	@Override
	public String toString() {
		return "Category [category_id=" + category_id + ", category=" + category + ", tag=" + tag + ", status=" + status
				+ "]";
	}
	
	/**
	 * Metodo para devolver el id de la categoria
	 * @param
	 * */
	public Integer getCategory_id() {
		return category_id;
	}
	
	
	/**
	 * Metodo para asignar el id de la categoria
	 * @param category_id, el id que se asignara
	 * */
	public void setCategory_id(Integer category_id) {
		this.category_id = category_id;
	}
	
	/**
	 * Metodo para devolver el nombre de la categoria
	 * @param
	 * */
	public String getCategory() {
		return category;
	}
	
	
	/**
	 * Metodo para asignar el nombre de la categoria
	 * @param category, el nombre que se asignara
	 * */
	public void setCategory(String category) {
		this.category = category;
	}
	
	/**
	 * Metodo para devolver el tag de la categoria
	 * @param
	 * */
	public String getTag() {
		return tag;
	}
	
	
	/**
	 * Metodo para asignar el tag de la categoria
	 * @param tag, el tag que se asignara
	 * */
	public void setTag(String tag) {
		this.tag = tag;
	}
	
	/**
	 * Metodo para devolver el status de la categoria
	 * @param
	 * */
	public Integer getStatus() {
		return status;
	}
	
	
	/**
	 * Metodo para asignar el status de la categoria
	 * @param status, el status que se asignara
	 * */
	public void setStatus(Integer status) {
		this.status = status;
	}
    
    
    
}
