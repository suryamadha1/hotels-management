package com.example.sprintOneGrpThree.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.sprintOneGrpThree.Entity.Staff;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Integer> {

}
