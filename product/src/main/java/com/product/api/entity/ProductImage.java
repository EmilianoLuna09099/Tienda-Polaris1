package com.product.api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name="product_image")
public class ProductImage {
	
	
	@Id
	   @GeneratedValue(strategy = GenerationType.IDENTITY)
	   @Column(name = "product_image_id")
	   private Integer productImageId;

	   @Column(name = "product_id")
	   private Integer productId;

	   @Column(name = "image")
	   private String image;

	   @Column(name = "status")
	   private Integer status;

	public Integer getProductImage_id() {
		return productImageId;
	}

	public Integer getProduct_id() {
		return productId;
	}

	public String getImage() {
		return image;
	}

	public Integer getStatus() {
		return status;
	}

	public void setProductImage_id(Integer productImageId) {
		this.productImageId = productImageId;
	}

	public void setProduct_id(Integer productId) {
		this.productId = productId;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}
