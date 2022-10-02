package com.example.sprintOneGrpThree.Service;

import java.util.List;
import java.util.Optional;

import com.example.sprintOneGrpThree.Entity.Customer;
import com.example.sprintOneGrpThree.Entity.Transaction;
import com.example.sprintOneGrpThree.Exception.CustomerScopeViolationException;
import com.example.sprintOneGrpThree.Exception.InvalidOperationException;
import com.example.sprintOneGrpThree.Exception.StaffScopeViolationException;

public interface CustomerService {

	String signUpCustomer(Customer c);

	String loginCustomer(Customer c);

	String updateCustomer(Customer c);

	List<Customer> getCustomerByEmail(String email);

	List<Customer> getAllCustomers() throws CustomerScopeViolationException;

	String deleteCustomer(String email) throws InvalidOperationException;

	List<Transaction> getTransactions() throws StaffScopeViolationException, InvalidOperationException;
	
	
	
	

}
