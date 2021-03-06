package com.sample.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sample.entities.Product;
import com.sample.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/customer/v1/")
@Slf4j
@CrossOrigin("*")
public class ProductServiceController {

	Logger log = LoggerFactory.getLogger(ProductServiceController.class);
	
	@Autowired
	ProductService productService;
	
	@ResponseBody
	private String home() {
		return "<h1>This is the Home page</h1>";
	}

	@GetMapping("findall")
	public List<Product> findall() {
		return productService.findAll();
	}

	@PostMapping("/create")
	public ResponseEntity<Product> create(@Valid @RequestBody Product product) {
		return ResponseEntity.ok(productService.create(product));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Product> findById(@PathVariable long id) {
		log.debug("Test");
		Optional<Product> product = productService.findById(id);
		if (!product.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(product.get());
	}

	@PostMapping(value = "/update")
	public ResponseEntity<Product> update(@Valid @RequestBody Product product) {
		Product p = productService.update(product);
		return p == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(p);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable long id) {
		Optional<Product> p = productService.findById(id);

		if (!p.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		productService.deleteById(id);
		return ResponseEntity.ok().build();
	}

}
