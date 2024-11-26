package com.crud_lombok_exception_postgres.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.crud_lombok_exception_postgres.entity.Product;
import com.crud_lombok_exception_postgres.exception.ResourceNotFoundException;
import com.crud_lombok_exception_postgres.repository.IProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

	private final IProductRepository productRepository;

	public Product createProduct(Product product) {
		return productRepository.save(product);
	}

	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	public Product updateProduct(Product product) {
		Product existingProduct = productRepository.findById(product.getId()).get();

		Product newProduct = existingProduct.toBuilder().name(product.getName()).description(product.getDescription())
				.category(product.getCategory()).build();

		productRepository.save(newProduct);
		return newProduct;
	}

	public Product getProductById(Long id) {
		return productRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Resource not found" + id));
	}

	public void deleteProduct(Long id) {
		if (!productRepository.existsById(id)) {
			throw new ResourceNotFoundException("Resource Not Found" + id);
		}
		productRepository.deleteById(id);
	}
}
