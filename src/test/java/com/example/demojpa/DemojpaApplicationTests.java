package com.example.demojpa;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.demojpa.controller.CustomerController;
import com.example.demojpa.model.Customer;
import com.example.demojpa.model.CustomerRepository;
import com.example.demojpa.service.exceptions.CustomerAlreadyExistsException;
import com.example.demojpa.service.exceptions.CustomerEmptyListException;
import com.example.demojpa.service.exceptions.CustomerNotExistException;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class DemojpaApplicationTests {
	@Autowired
	private CustomerRepository repository;

	private CustomerController controller;
	
	@Test
	public void TestAddCustomers() {
		boolean condition1, condition2;
		controller = new CustomerController(repository);
		try {
			controller.add(new Customer("Jack", "Bauer"));			
			condition1 = (controller.findByFirstName("Jack") != null) ? true : false;
			condition2 = (controller.findByLastName("Bauer") != null) ? true : false;
			assertTrue(condition1 == condition2);
		}catch(CustomerAlreadyExistsException | CustomerNotExistException cae) {
			System.out.println(cae.getMessage());
			assertTrue(false);
		}
	}
	
	@Test
	public void TestFindCustomerById() {
		long id=1;
		boolean condition1, condition2;
		controller = new CustomerController(repository);
		try {
			controller.add(new Customer("Jack", "Bauer"));
			condition1 = controller.findById(id).getFirstName().equals("Jack");
			condition2 = controller.findById(id).getLastName().equals("Bauer");
			assertTrue(condition1 == condition2);
		}catch(CustomerNotExistException | CustomerAlreadyExistsException cnee) {
			System.out.println(cnee.getMessage());
			assertTrue(false);
		} 
	}
	
	@Test
	public void TestListAllCustomers() {
		controller = new CustomerController(repository);
		
		try {
			controller.add(new Customer("Jack", "Bauer"));
			controller.add(new Customer("Chloe", "O'Brian"));
			controller.add(new Customer("Kim", "Bauer"));
			assertTrue(true);
		}catch(CustomerAlreadyExistsException cnee) {
			assertTrue(false);
		}

		try {
			boolean condition = (controller.list().size()==3) ? true:false;
			assertTrue(condition);
		}catch (CustomerEmptyListException cele) {
			assertTrue(false);
		}
	}
	
}
