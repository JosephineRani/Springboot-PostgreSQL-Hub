package com.crud_lombok_exception_postgres.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.crud_lombok_exception_postgres.entity.Product;
import com.crud_lombok_exception_postgres.service.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/product")
@CrossOrigin("*")
@RequiredArgsConstructor
public class ProductController {

	private final ProductService productService;

	@PostMapping("/add")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Product> createProduct(@RequestBody Product product) {
		Product productObj = productService.createProduct(product);
		return new ResponseEntity<Product>(productObj, HttpStatusCode.valueOf(200));
	}

	@GetMapping("/get")
	public List<Product> getAllProduct() {
		return productService.getAllProducts();
	}

	@PutMapping("/update")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
		Product productObj = productService.updateProduct(product);
		return ResponseEntity.ok(productObj);
	}

	@GetMapping("/get-product/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable Long id) {
		Product product = productService.getProductById(id);
		return ResponseEntity.ok(product);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
		productService.deleteProduct(id);
		return ResponseEntity.noContent().build();
	}
}
