package com.example.sprintOneGrpThree.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sprintOneGrpThree.Entity.Enquiry;
import com.example.sprintOneGrpThree.Entity.Hotel;
import com.example.sprintOneGrpThree.Repository.EnquiryRepository;
@Service
public class EnquiryServiceImpl implements EnquiryService{

	@Autowired
	private EnquiryRepository enquiryRepository;
	
	@Override
	public void delete(int id) {
		enquiryRepository.deleteById(id); 
	}

	@Override
	public List<Enquiry> getEnquiryByEmail(String email) {
		List<Enquiry> enquiryList = enquiryRepository.findByEmail(email);
		return enquiryList;
	}

}
