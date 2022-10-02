package com.example.sprintOneGrpThree.Repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.sprintOneGrpThree.Entity.Enquiry;

@Repository
@Transactional
public interface EnquiryRepository extends JpaRepository<Enquiry, Integer> {

	List<Enquiry> findByEmail(String email);

}

