package com.product.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.product.api.entity.Category;

import jakarta.transaction.Transactional;


/*
 * Clase relacionada con la base de datos para hacer incersiones, acutalizaciones
 * */
@Repository
public interface RepoCategory extends JpaRepository<Category,Integer>{
	
	/*
	 * Metodo para obtener todas las categorias
	 * @param
	 * */
	@Query(value="SELECT * FROM  category ORDER BY category", nativeQuery=true)
	List<Category> getCategories();
	
	/*
	 * Metodo para obtener las caategorias con status 1
	 * @param
	 * */
	@Query(value = "SELECT * FROM category WHERE status = 1 ORDER BY category", nativeQuery = true)
	List<Category> getActiveCategories();
	
	
	/*
	 * Metodo para obtener la categoria dado su id
	 * @param category_id, id de la categoria que se busca
	 * */
	@Query(value = "SELECT * FROM category WHERE category_id = :category_id ORDER BY category", nativeQuery = true)
	Category getCategory(@Param("category_id") Integer category_id);
	
	
	/*
	 * Metodo para insertar la categoria a la base de datos
	 * @param category_id, id de la categoria a insertar
	 * @param tag, tag de la categoria a insertar
	 * */
	@Modifying
	@Transactional
	@Query(value = "INSERT INTO category (category, tag, status) VALUES (:category, :tag, 1)", nativeQuery = true)
	void createCategory(@Param("category") String category, @Param("tag") String tag);
	
	
	/*
	 * Metodo para actualizar la categoria de la base de datos
	 * @param category_id, id de la categoria a actualizar
	 * @param tag, tag de la categoria a actualizar
	 * @param status, status de la categoria a actualizar
	 * */
	@Modifying
	@Transactional
	@Query(value = "UPDATE category SET category = :category, tag = :tag WHERE category_id = :category_id;", nativeQuery = true)
	void updateCategory(@Param("category_id") Integer category_id, @Param("category") String category, @Param("tag") String tag);
	
	/*
	 * Metodo para habilitar la categoria de la base de datos
	 * @param category_id, id de la categoria a habilitar
	 * */
	@Modifying
	@Transactional
	@Query(value = "UPDATE category SET status = 1 WHERE category_id = :category_id;", nativeQuery = true)
	void enableCategory(@Param("category_id") Integer category_id);
	
	/*
	 * Metodo para deshabilitar la categoria de la base de datos
	 * @param category_id, id de la categoria a deshabilitar
	 * */
	@Modifying
	@Transactional
	@Query(value = "UPDATE category SET status = 0 WHERE category_id = :category_id;", nativeQuery = true)
	void disableCategory(@Param("category_id") Integer category_id);
	
	
}