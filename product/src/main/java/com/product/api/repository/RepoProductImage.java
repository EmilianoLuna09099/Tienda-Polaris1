package com.product.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.product.api.entity.Product;
import com.product.api.entity.ProductImage;

public interface RepoProductImage extends JpaRepository<ProductImage,Integer>{
	
	/*
	 * Metodo para obtener la imagen requerida
	 * @param product_id, el identificador de la imagen del producto
	 * */
	@Query(value = "SELECT * FROM product_image WHERE product_id = :product_id;", nativeQuery = true)
	ProductImage findByProduct_id(Integer product_id);
	
	/*
	 * Metodo para obtener las imagenes relacionadas a un producto
	 * @param product_id, el identificador de la imagen del producto
	 * */
	@Query(value="SELECT * FROM product_image WHERE product_id = :product_id",nativeQuery=true) // Consulta personalizada opcional
    List<ProductImage> findAllProducts(Integer product_id);
	
}
