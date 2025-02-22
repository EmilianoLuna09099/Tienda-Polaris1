package com.product.api.controller;


import com.product.api.entity.*;
import com.product.api.service.SvcCategory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")

public class CtrlProduct {
	
	@Autowired
	SvcCategory svc;
	
	@GetMapping
	public List<Category> getCategorias() {
		return svc.getCategories();
	}

	
}
