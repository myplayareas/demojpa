package com.example.demojpa.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demojpa.model.Customer;
import com.example.demojpa.model.CustomerRepository;
import com.example.demojpa.service.exceptions.CustomerAlreadyExistsException;
import com.example.demojpa.service.exceptions.CustomerEmptyListException;
import com.example.demojpa.service.exceptions.CustomerNotExistException;

@Service
public class CustomerService {
	@Autowired
	private CustomerRepository repository;
					
	public CustomerService(CustomerRepository repository) {
		this.repository = repository;
	}

	/**
	 * Add a Customer in repository
	 * @param customer Customer
	 * @throws CustomerAlreadyExistsException
	 */
	public void add(Customer customer) throws CustomerAlreadyExistsException{
		List<Customer> checkLastName = repository.findByLastName(customer.getLastName());
		List<Customer> checkFirstName = repository.findByFirstName(customer.getFirstName());
		
		if ( (!checkLastName.isEmpty() ) && (!checkFirstName.isEmpty() ) ) {
			throw new CustomerAlreadyExistsException("Customer already exists!");
		}
		repository.save(customer);
	}
	
	/**
	 * List all Customers from repository
	 * @return List<Customer>
	 */
	public List<Customer> list() throws CustomerEmptyListException{
		List<Customer> customerList = new LinkedList<Customer>();
		
		if (repository.findAll() == null) {
			throw new CustomerEmptyListException("There is no customer in the repository!");
		}
		
		for (Customer customer : repository.findAll()) {
			customerList.add(customer);
		}
		return customerList;
	}
	
	/**	 
	 * Recovery the custormer by id
	 * @param id long
	 * @return customer
	 * @throws CustomerNotExistException
	 */
	public Customer findById(long id) throws CustomerNotExistException{
		if (repository.findById(id) == null) {
			throw new CustomerNotExistException("Customer does not exist!");
		}
		return repository.findById(id);
	}

	public List<Customer> findByFirstName(String name) throws CustomerNotExistException{
		if (repository.findByFirstName(name) == null) {
			throw new CustomerNotExistException("Customer does not exist!");
		}
		return repository.findByFirstName(name);
	}

	public List<Customer> findByLastName(String name) throws CustomerNotExistException{
		if (repository.findByLastName(name) == null) {
			throw new CustomerNotExistException("Customer does not exist!");
		}
		return repository.findByLastName(name);
	}

}