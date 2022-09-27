package com.example.sprintOneGrpThree.Service;

import java.util.List;
import java.util.Optional;

import com.example.sprintOneGrpThree.Entity.Customer;

public interface CustomerService {

	String signUpCustomer(Customer c);

	String loginCustomer(Customer c);

	String updateCustomer(Customer c);

	List<Customer> getCustomerByEmail(String email);

	List<Customer> getAllCustomers();

	String deleteCustomer(String email);
	
	
	
	

}
