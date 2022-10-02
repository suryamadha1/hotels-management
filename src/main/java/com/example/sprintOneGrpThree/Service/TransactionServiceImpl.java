package com.example.sprintOneGrpThree.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sprintOneGrpThree.Entity.Customer;
import com.example.sprintOneGrpThree.Entity.Hotel;
import com.example.sprintOneGrpThree.Entity.Transaction;
import com.example.sprintOneGrpThree.Repository.CustomerRepository;
import com.example.sprintOneGrpThree.Repository.HotelRepository;
import com.example.sprintOneGrpThree.Repository.TransactionRepository;

@Service
public class TransactionServiceImpl implements TransactionService{

	
	@Autowired
	private TransactionRepository transactionRepository;
	
	@Autowired
	private HotelRepository hotelRepository;
	
	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public String addTransaction(Transaction t) {
		Hotel h = hotelRepository.findById(t.getHotel().getId()).get();
		Customer c = customerRepository.findById(t.getCustomer().getCustomerId()).get();
		t.setCustomer(c);
		t.setHotel(h);
		transactionRepository.save(t);
		return "transaction saved";
	}
}
