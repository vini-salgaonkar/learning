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

import com.sample.entities.Customer;
import com.sample.service.CustomerService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/customer/v1/")
@Slf4j
@CrossOrigin("*")
public class CustomerServiceController {

	Logger log = LoggerFactory.getLogger(CustomerServiceController.class);
	
	@Autowired
	CustomerService customerService;
	
	@ResponseBody
	private String home() {
		return "<h1>This is the Home page</h1>";
	}

	@GetMapping("findall")
	public List<Customer> findall() {
		return customerService.findAll();
	}

	@PostMapping("/create")
	public ResponseEntity<Customer> create(@Valid @RequestBody Customer product) {
		return ResponseEntity.ok(customerService.create(product));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Customer> findById(@PathVariable long id) {
		log.debug("Test");
		Optional<Customer> product = customerService.findById(id);
		if (!product.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(product.get());
	}

	@PostMapping(value = "/update")
	public ResponseEntity<Customer> update(@Valid @RequestBody Customer product) {
		Customer p = customerService.update(product);
		return p == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(p);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable long id) {
		Optional<Customer> p = customerService.findById(id);

		if (!p.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		customerService.deleteById(id);
		return ResponseEntity.ok().build();
	}

}
