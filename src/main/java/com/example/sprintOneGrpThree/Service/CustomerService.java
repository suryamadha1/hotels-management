package com.example.sprintOneGrpThree.Service;

import java.util.List;

import com.example.sprintOneGrpThree.Entity.Customer;
import com.example.sprintOneGrpThree.Exception.CouponDoesNotExistException;
import com.example.sprintOneGrpThree.Exception.CustomerScopeViolationException;

public interface CustomerService {
	
	String signUpCustomer(Customer c);

	String loginCustomer(Customer c);

	String updateCustomer(Customer c);

	List<Customer> getCustomerByEmail(String email);

	List<Customer> getAllCustomers() throws CustomerScopeViolationException;

	String deleteCustomer(String email);

}
