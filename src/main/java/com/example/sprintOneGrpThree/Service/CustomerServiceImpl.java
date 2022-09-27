package com.example.sprintOneGrpThree.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sprintOneGrpThree.Entity.Customer;
import com.example.sprintOneGrpThree.Entity.Session;
import com.example.sprintOneGrpThree.Repository.CustomerRepository;
import com.example.sprintOneGrpThree.Repository.SessionRepository;
import com.example.sprintOneGrpThree.Repository.TestimonialRepository;

@Service
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private SessionRepository sessionRepository;
	
	@Autowired
	private TestimonialRepository testimonialRepository;

	@Override
	public String signUpCustomer(Customer c) {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		List<Customer> customers = customerRepository.findAll();
		boolean res = false;
		if(!customers.isEmpty()) {
		res = customers.stream().filter(f->f.getEmail().equals(c.getEmail())).anyMatch(
				n->n.getPasscode().equals(c.getPasscode()));
		}
		if(res) {
			Session obj = new Session(c.getEmail(),"customer",c.getCustomerId());
			sessionRepository.deleteAll();
			sessionRepository.save(obj);
			return "login successful";
		}
		else {
			return "login failed";
		}
	}

	@Override
	public String updateCustomer(Customer cust) {
		
		if(sessionRepository.count()==0) {
			return "Update operation cannot be performed";
		}
		Session obj = sessionRepository.findAll().stream().findFirst().get();
	
		if(!cust.getEmail().equals(obj.getEmail())) {
				return "Email cannot be udpated";
			}
	
		customerRepository.save(new Customer(obj.getId(),
				cust.getName(),
				cust.getEmail(),
				cust.getGender(),
				cust.getPasscode(),
				cust.getMobile()));
		return "Your details are updated";
		}

	@Override
	public List<Customer> getCustomerByEmail(String email) {
		List<Customer> custList = customerRepository.findByEmail(email);
		return custList;
	}

	@Override
	public List<Customer> getAllCustomers() {
		List<Customer> custList = customerRepository.findAll();
		return custList;
	}

	@Override
	public String deleteCustomer(String email) {
		List<Customer> c  = customerRepository.findByEmail(email);
		if(c.isEmpty()) {
			return "Customer with given email id not found";
		}
		List<Integer> list = testimonialRepository.findAll().stream().filter(n->n.getCustomer().getEmail().equals(
				email)).map(n->n.getTestimonialId()).collect(Collectors.toList());
		testimonialRepository.deleteAllById(list);
		
		customerRepository.deleteById(c.get(0).getCustomerId());
		return "Customer record deleted";
	}
		

}
