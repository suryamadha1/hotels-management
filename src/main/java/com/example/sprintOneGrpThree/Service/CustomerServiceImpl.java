package com.example.sprintOneGrpThree.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sprintOneGrpThree.Entity.Coupon;
import com.example.sprintOneGrpThree.Entity.Customer;
import com.example.sprintOneGrpThree.Entity.Session;
import com.example.sprintOneGrpThree.Entity.Staff;
import com.example.sprintOneGrpThree.Entity.Transaction;
import com.example.sprintOneGrpThree.Exception.CustomerScopeViolationException;
import com.example.sprintOneGrpThree.Exception.InvalidOperationException;
import com.example.sprintOneGrpThree.Exception.StaffScopeViolationException;
import com.example.sprintOneGrpThree.Repository.CouponRepository;
import com.example.sprintOneGrpThree.Repository.CustomerRepository;
import com.example.sprintOneGrpThree.Repository.SessionRepository;
import com.example.sprintOneGrpThree.Repository.TestimonialRepository;
import com.example.sprintOneGrpThree.Repository.TransactionRepository;

@Service
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private SessionRepository sessionRepository;
	
	@Autowired
	private TestimonialRepository testimonialRepository;
	
	@Autowired
	private CouponRepository couponRepository;
	
	@Autowired
	private TransactionRepository transactionRepository;

	@Override
	public String signUpCustomer(Customer c) {
		// TODO Auto-generated method stub
		
		List<Customer> customers = customerRepository.findAll();
		boolean res = false;
		if(!customers.isEmpty()) {
		res = customers.stream().anyMatch(n->n.getEmail().equals(c.getEmail()));
		}
		Coupon coupon = new Coupon();
		coupon.setCoupon_id(1);
		coupon.setAmount(0.0);
		coupon.setName("WELCOME10%OFF");
		coupon.setPercentage(10);
		List<Coupon> couponList = new ArrayList<>();
		couponList.add(coupon);
		c.setCoupons(couponList);
		couponRepository.save(coupon);
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
		if(sessionRepository.count()==0) {
			return "Operation cannot be performed";
		}
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
				cust.getMobile(),
				cust.getCoupons()));
		return "Your details are updated";
		}

	@Override
	public List<Customer> getCustomerByEmail(String email) {
		List<Customer> custList = customerRepository.findByEmail(email);
		return custList;
	}

	@Override
	public List<Customer> getAllCustomers() throws CustomerScopeViolationException {
		boolean res = sessionRepository.findAll().stream().anyMatch(n->n.getType().equals("staff"));
		if(!res) {
			throw new CustomerScopeViolationException();
		}
		List<Customer> custList = customerRepository.findAll();
		return custList;
	}

	@Override
	public String deleteCustomer(String email) throws InvalidOperationException {
		if(sessionRepository.count()==0) {
			throw new InvalidOperationException();
		}
		List<Customer> c  = customerRepository.findByEmail(email);
		if(c.isEmpty()) {
			return "Customer with given email id not found";
		}
		List<Integer> list = testimonialRepository.findAll().stream().filter(n->n.getCustomer().getEmail().equals(
				email)).map(n->n.getTestimonialId()).collect(Collectors.toList());
		testimonialRepository.deleteAllById(list);
		
		List<Integer> listTransaction = transactionRepository.findAll().stream().filter(n->n.getCustomer().getCustomerId()==c.get(0).getCustomerId()).map(n->n.getId()).collect(Collectors.toList());
		transactionRepository.deleteAllById(listTransaction);
		
		customerRepository.deleteById(c.get(0).getCustomerId());
		return "Customer record deleted";
	}

	@Override
	public List<Transaction> getTransactions() throws StaffScopeViolationException, InvalidOperationException {
		if(sessionRepository.count()==0) {
			throw new InvalidOperationException();
		}
		boolean res = sessionRepository.findAll().stream().anyMatch(n->n.getType().equals("staff"));
		if(res) {
			throw new StaffScopeViolationException();
		}
		Session obj = sessionRepository.findAll().stream().findFirst().get();
		List<Transaction> transactionList = transactionRepository.findAll().stream().filter(n->n.getCustomer().getCustomerId()==obj.getId()).collect(Collectors.toList());
		return transactionList;
	}
		

}
