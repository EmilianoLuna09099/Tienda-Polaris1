package com.product.api.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.product.api.dto.DtoCategoryIn;
import com.product.api.entity.Category;

public interface SvcCategory{
	
	public ResponseEntity< List<Category>> getCategories();
	
	public ResponseEntity<List<Category>> getActiveRegions();
	public ResponseEntity<ApiResponse> createRegion(DtoCategoryIn in);
	public ResponseEntity<ApiResponse> updateRegion(DtoCategoryIn in, Integer id);
	public ResponseEntity<ApiResponse> enableRegion(Integer id);
	public ResponseEntity<ApiResponse> disableRegion(Integer id);

	
}