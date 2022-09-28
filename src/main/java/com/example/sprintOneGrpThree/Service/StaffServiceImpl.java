package com.example.sprintOneGrpThree.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sprintOneGrpThree.Repository.StaffRepository;


@Service
public class StaffServiceImpl implements StaffService{
	
	@Autowired
	private StaffRepository staffRepository;
	
	
	@Override
	public void delete(int id) {
		staffRepository.deleteById(id); 
	}
}
