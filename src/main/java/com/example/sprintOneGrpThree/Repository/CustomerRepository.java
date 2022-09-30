package com.example.sprintOneGrpThree.Repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.sprintOneGrpThree.Entity.Customer;

@Repository
@Transactional
public interface CustomerRepository extends JpaRepository<Customer, Integer>{
	
	List<Customer> findByEmail(String email);

}
