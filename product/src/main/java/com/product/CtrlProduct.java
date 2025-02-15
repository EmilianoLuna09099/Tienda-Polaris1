package com.product;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")

public class CtrlProduct {
	
	
	@GetMapping
	public ArrayList<Category> getCategorias() {
		ArrayList<Category> categorias = new ArrayList<>();
		categorias.add(new Category(1,"Videojuegos","VJ"));
		categorias.add(new Category(2,"Lentes","L"));
		categorias.add(new Category(3,"Relojes","R"));
		categorias.add(new Category(4,"Rompecabezas","RP"));
		
		return categorias;
		/*for(Category c: categorias) {
			System.out.println(c.toString());
		}*/
	}

	
}
