package com.example.sprintOneGrpThree.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.sprintOneGrpThree.Entity.Customer;
import com.example.sprintOneGrpThree.Entity.Staff;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Integer>{
	
	List<Staff> findByEmail(String email);
}
