package com.product.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.product.api.dto.out.DtoProductOut;
import com.product.api.entity.Product;


@Repository
public interface RepoProduct extends JpaRepository<Product, Integer>{
	
	/*
	 * Metodo para obtener el producto deseado
	 * @param product_id, el identificador del producto deseado
	 * */
	@Query(value = "SELECT p.product_id, p.gtin, p.product, p.description, p.price, p.stock, p.category_id, c.category, p.status "
			+ "FROM product p  "
			+ "INNER JOIN category c ON c.category_id = p.category_id "
				+ "WHERE p.product_id = :product_id;", nativeQuery = true)
			DtoProductOut getProduct(Integer product_id);
}
