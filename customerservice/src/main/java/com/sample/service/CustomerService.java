package com.sample.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sample.entities.Customer;
import com.sample.entities.CustomerRepository;


@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	public List<Customer> findAll() {
		return customerRepository.findAll();
	}

	public Optional<Customer> findById(long id) {
		return customerRepository.findById(id);
	}

	public Customer update(Customer customer) {
		Optional<Customer> p = this.findById(customer.getId());
		if (!p.isPresent()) {
			return null;
		}
		p.get().setCode(Objects.isNull(customer.getCode()) ? p.get().getCode() : customer.getCode());
		p.get().setName(Objects.isNull(customer.getName()) ? p.get().getName() : customer.getName());
		return customerRepository.save(customer);
	}

	public Customer create(Customer product) {

		return customerRepository.save(product);
	}

	public void deleteById(long id) {
		customerRepository.deleteById(id);
	}

}
