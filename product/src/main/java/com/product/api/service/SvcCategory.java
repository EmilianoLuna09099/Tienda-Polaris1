package com.product.api.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.product.api.entity.*;

public interface SvcCategory{
	
	public ResponseEntity< List<Category>> getCategories();
}