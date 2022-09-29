package com.example.sprintOneGrpThree.Service;

import com.example.sprintOneGrpThree.Entity.Customer;
import com.example.sprintOneGrpThree.Exception.CouponDoesNotExistException;

public interface CustomerService {
	
	String signUpCustomer(Customer c) throws CouponDoesNotExistException;

	String loginCustomer(Customer c);

}
