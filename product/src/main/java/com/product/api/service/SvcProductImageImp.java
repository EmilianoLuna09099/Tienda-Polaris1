package com.product.api.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.product.api.dto.in.DtoProductImageIn;
import com.product.api.entity.Product;
import com.product.api.entity.ProductImage;
import com.product.api.repository.RepoProductImage;
import com.product.common.dto.ApiResponse;
import com.product.exception.ApiException;
import com.product.exception.DBAccessException;

@Service
public class SvcProductImageImp implements SvcProductImage{
	
	@Autowired
	RepoProductImage repo;
	
	@Value("${app.upload.dir}")
	private String uploadDir;
	
	/*
	 * Metodo para guardar la imagen en base 64 en la base de datos
	 * @param in, el cuerpo JSON con la imagen y el id
	 * */
	@Override
	public ResponseEntity<ApiResponse> uploadProductImage(DtoProductImageIn in) {
		
		try {
			// Eliminar el prefijo "data:image/png;base64," si existe
			if (in.getImage().startsWith("data:image")) {
			int commaIndex = in.getImage().indexOf(",");
				if (commaIndex != -1) {
					in.setImage(in.getImage().substring(commaIndex + 1));
				}
			}

			
			// Decodifica la cadena Base64 a bytes
			byte[] imageBytes = Base64.getDecoder().decode(in.getImage());

			// Genera un nombre único para la imagen (se asume extensión PNG)
			String fileName = UUID.randomUUID().toString() + ".png";

			// Construye la ruta completa donde se guardará la imagen
			Path imagePath = Paths.get(uploadDir, "img", "product", fileName);
		    
			// Asegurarse de que el directorio exista
			Files.createDirectories(imagePath.getParent());

			// Escribir el archivo en el sistema de archivos
			Files.write(imagePath, imageBytes);
			
			//Por checar ojo
			ProductImage productImage = repo.findByProduct_id(in.getProduct_id());
			

				// Crear la entidad productImage y guardar la URL en la base de datos
				productImage = new ProductImage();
				productImage.setProduct_id(in.getProduct_id());
				productImage.setImage("/uploads/img/product/" + fileName);
				productImage.setStatus(1); 

				// Guardar la ruta de la imagen
				repo.save(productImage);
			
				productImage.setImage("/uploads/img/product/" + fileName);
				repo.save(productImage);
			
			
			return new ResponseEntity<>(new ApiResponse("La imagen ha sido actualizada"), HttpStatus.OK);
		}catch (DataAccessException e) {
		    throw new DBAccessException(e);
		}catch (IOException e) {
			throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al guardar el archivo");
		}
	}

	/*
	 * Metodo para desactivar una imagen
	 * @param product_id, el identificador de la imagen del producto
	 * */
	@Override
	public ResponseEntity<ApiResponse> deleteProductImage(Integer id) {
		try {
			validateProductId(id);
			ProductImage productImage = repo.findById(id).get();
			productImage.setStatus(0);
			repo.save(productImage);
			return new ResponseEntity<>(new ApiResponse("la imagen ha sido desactivada"), HttpStatus.OK);
		}catch (DataAccessException e) {
			throw new DBAccessException(e);
		}
	}
	
	/*
	 * Metodo para activar una imagen
	 * @param product_id, el identificador de la imagen del producto
	 * */
	@Override
	public ResponseEntity<ApiResponse> enableProductImage(Integer id) {
		try {
			validateProductId(id);
			ProductImage productImage = repo.findById(id).get();
			productImage.setStatus(1);
			repo.save(productImage);
			return new ResponseEntity<>(new ApiResponse("la imagen ha sido activado"), HttpStatus.OK);
		}catch (DataAccessException e) {
			throw new DBAccessException(e);
		}
	}
	
	
	/*
	 * Metodo para obtener una imagen
	 * @param product_id, el identificador de la imagen del producto
	 * */
	@Override
	public ResponseEntity<ApiResponse> getProductImage(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private void validateProductId(Integer id) {
		try {
			if(repo.findById(id).isEmpty()) {
				throw new ApiException(HttpStatus.NOT_FOUND, "El id del producto no existe");
			}
		}catch (DataAccessException e) {
			throw new DBAccessException(e);
		}
	}

}
