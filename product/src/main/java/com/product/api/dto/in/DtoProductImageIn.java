package com.product.api.dto.in;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;

public class DtoProductImageIn {
	
	@JsonProperty("product_id")
	@NotNull(message="El productId es obligatorio")
	private Integer productId;


	@JsonProperty("image")
	@NotNull(message="El image es obligatorio")
	private String image;


	public Integer getProduct_id() {
		return productId;
	}

	public String getImage() {
		return image;
	}

	public void setProduct_id(Integer productId) {
		this.productId = productId;
	}

	public void setImage(String image) {
		this.image = image;
	}

}
