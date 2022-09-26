package com.example.sprintOneGrpThree.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sprintOneGrpThree.Entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
