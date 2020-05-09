package com.example.demojpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demojpa.controller.CustomerController;
import com.example.demojpa.model.Customer;
import com.example.demojpa.model.CustomerRepository;
import com.example.demojpa.service.exceptions.CustomerAlreadyExistsException;
import com.example.demojpa.service.exceptions.CustomerEmptyListException;
import com.example.demojpa.service.exceptions.CustomerNotExistException;

@SpringBootApplication
public class DemojpaApplication {
	private static CustomerController controller;
	
	/**
	 * Este bean cria e carrega automaticamente o repository
	 * @param repository CustomerRepository
	 * @return CommandLineRunner
	 */
	@Bean
	public CommandLineRunner loadRepository(CustomerRepository repository) {		
		return (args) -> {
			controller = new CustomerController(repository);
			System.out.println("Criado o repositorio para armazenar os dados");
		};
	}
	
	public static void addCustomers() {
		try {
			System.out.println("Adding Jack");controller.add(new Customer("Jack", "Bauer"));
			System.out.println("Adding Chloe");controller.add(new Customer("Chloe", "O'Brian"));
			System.out.println("Adding Kim");controller.add(new Customer("Kim", "Bauer"));
			System.out.println("Adding David");controller.add(new Customer("David", "Palmer"));
			System.out.println("Adding Michelle");controller.add(new Customer("Michelle", "Dessler"));
		}catch(CustomerAlreadyExistsException cae) {
			System.out.println(cae.getMessage());
		}
	}
	
	public static void findCustomerById(long id) {
		try {
			System.out.println("Find customer with id="+id);
			System.out.println("Name" + controller.findById(id).getFirstName());
		}catch(CustomerNotExistException cnee) {
			System.out.println(cnee.getMessage());
		} 
	}
	
	public static void listAllCustomers() {
		try {
			System.out.println("Listing all customers");
			for (Customer customer : controller.list()) {
				System.out.println("Customer: " + customer.getId() + " Name: " + customer.getFirstName() + " Last Name: " + customer.getLastName());
			}			
		}catch (CustomerEmptyListException cele) {
			System.out.println(cele);
		}
	}
	
	public static void main(String[] args) {
		SpringApplication.run(DemojpaApplication.class, args);
		addCustomers();
		listAllCustomers();
		findCustomerById(2);
	}

}