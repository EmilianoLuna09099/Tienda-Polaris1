package com.product.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.product.api.entity.Category;

import jakarta.transaction.Transactional;

@Repository
public interface RepoCategory extends JpaRepository<Category,Integer>{
	
	@Query(value="SELECT * FROM  category ORDER BY category", nativeQuery=true)
	List<Category> getCategories();
	
	@Query(value = "SELECT * FROM category WHERE status = 1 ORDER BY category", nativeQuery = true)
	List<Category> getActiveCategories();
	
	@Query(value = "SELECT * FROM category WHERE category_id = :category_id ORDER BY category", nativeQuery = true)
	Category getCategory(@Param("category_id") Integer category_id);
	
	@Modifying
	@Transactional
	@Query(value = "INSERT INTO category (category, tag, status) VALUES (:category, :tag, 1)", nativeQuery = true)
	void createCategory(@Param("category") String category, @Param("tag") String tag);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE category SET category = :category, tag = :tag WHERE category_id = :category_id;", nativeQuery = true)
	void updateCategory(@Param("region_id") Integer category_id, @Param("category") String category, @Param("tag") String tag);

	@Modifying
	@Transactional
	@Query(value = "UPDATE category SET status = 1 WHERE category_id = :category_id;", nativeQuery = true)
	void enableCategory(@Param("Category_id") Integer category_id);

	@Modifying
	@Transactional
	@Query(value = "UPDATE category SET status = 0 WHERE category_id = :category_id;", nativeQuery = true)
	void disableCategory(@Param("Category_id") Integer category_id);
	
	
}