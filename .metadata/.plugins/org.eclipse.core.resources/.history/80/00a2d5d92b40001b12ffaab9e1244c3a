package com.sample.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sample.entities.Product;
import com.sample.entities.ProductRepository;


@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	public List<Product> findAll() {
		return productRepository.findAll();
	}

	public Optional<Product> findById(long id) {
		return productRepository.findById(id);
	}

	public Product update(Product product) {
		Optional<Product> p = this.findById(product.getId());
		if (!p.isPresent()) {
			return null;
		}
		p.get().setCode(Objects.isNull(product.getCode()) ? p.get().getCode() : product.getCode());
		p.get().setName(Objects.isNull(product.getName()) ? p.get().getName() : product.getName());
		p.get().setPrice(Objects.isNull(product.getPrice()) ? p.get().getPrice() : product.getPrice());
		p.get().setDescription(String.valueOf(product.getDescription()).isEmpty() ? p.get().getDescription()
				: product.getDescription());
		return productRepository.save(product);
	}

	public Product create(Product product) {

		return productRepository.save(product);
	}

	public void deleteById(long id) {
		productRepository.deleteById(id);
	}

}
