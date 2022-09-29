package com.example.sprintOneGrpThree.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sprintOneGrpThree.Entity.Coupon;
import com.example.sprintOneGrpThree.Entity.Customer;
import com.example.sprintOneGrpThree.Entity.Session;
import com.example.sprintOneGrpThree.Exception.CouponDoesNotExistException;
import com.example.sprintOneGrpThree.Repository.CouponRepository;
import com.example.sprintOneGrpThree.Repository.CustomerRepository;
import com.example.sprintOneGrpThree.Repository.SessionRepository;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private SessionRepository sessionRepository;
	
	@Autowired
	private CouponRepository couponRepository;
	
	@Override
	public String signUpCustomer(Customer c) throws CouponDoesNotExistException {
		
		List<Coupon> coupons = couponRepository.findAll();
		
		List<Customer> customers = customerRepository.findAll();
		boolean res = false;
		if(!customers.isEmpty()) {
		res = customers.stream().anyMatch(n->n.getEmail().equals(c.getEmail()));
		}
		if(res) {
			return "Email id already exists";
		}
		else {
			customerRepository.save(c);
			sessionRepository.deleteAll();
			Session obj = new Session(c.getEmail(),"customer",c.getCustomerId());
			sessionRepository.save(obj);
			return "SignUp successful !!";
		}
		
	}

	@Override
	public String loginCustomer(Customer c) {
		List<Customer> customers = customerRepository.findAll();
		List<Customer> storeId = null;
		boolean res = false;
		if(!customers.isEmpty()) {
		res = customers.stream().filter(f->f.getEmail().equals(c.getEmail())).anyMatch(
				n->n.getPasscode().equals(c.getPasscode()));
		storeId = customerRepository.findAll().stream().filter(n->n.getEmail().equals(c.getEmail())).collect(
				Collectors.toList());
		}
		if(res) {
			Session obj = new Session(c.getEmail(),"customer",storeId.get(0).getCustomerId());
			sessionRepository.deleteAll();
			sessionRepository.save(obj);
			return "login successful";
		}
		else {
			return "login failed";
		}
	}

}
