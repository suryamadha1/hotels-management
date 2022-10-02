package com.example.sprintOneGrpThree.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.sprintOneGrpThree.Entity.Enquiry;

@Service
public interface EnquiryService {

	public void delete(int id);

	public List<Enquiry> getEnquiryByEmail(String email);

}
