package com.example.demojpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.example.demojpa.model.Customer;
import com.example.demojpa.model.CustomerRepository;
import com.example.demojpa.service.CustomerService;
import com.example.demojpa.service.exceptions.CustomerAlreadyExistsException;
import com.example.demojpa.service.exceptions.CustomerEmptyListException;
import com.example.demojpa.service.exceptions.CustomerNotExistException;

@Controller
public class CustomerController {
	@Autowired
	private CustomerService service;
	
	public CustomerController(CustomerRepository repository) {
		this.service = new CustomerService(repository);
	}
	
	/**
	 * Add a Customer
	 * @param customer Customer
	 */
	public void add(Customer customer) throws CustomerAlreadyExistsException{
		service.add(customer);
	}
	
	/**
	 * List all Customers from repository
	 * @return list<Customer>
	 * @throws CustomerEmptyListException 
	 */
	public List<Customer> list() throws CustomerEmptyListException{
		return service.list();
	}
	
	/**
	 * Recovery the customer by id
	 * @param id long
	 * @return customer
	 * @throws CustomerNotExistException
	 */
	public Customer findById(long id) throws CustomerNotExistException{
		return service.findById(id);
	}

	/**
	 * 
	 * @param name
	 * @return
	 * @throws CustomerNotExistException
	 */
	public List<Customer> findByFirstName(String name) throws CustomerNotExistException {
		return service.findByFirstName(name);
	}
	
	/**
	 * 
	 * @param name
	 * @return
	 * @throws CustomerNotExistException
	 */
	public List<Customer> findByLastName(String name) throws CustomerNotExistException {
		return service.findByLastName(name);
	}

}
