package com.example.sprintOneGrpThree.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sprintOneGrpThree.Entity.Customer;
import com.example.sprintOneGrpThree.Entity.Session;
import com.example.sprintOneGrpThree.Entity.Staff;
import com.example.sprintOneGrpThree.Exception.CustomerScopeViolationException;
import com.example.sprintOneGrpThree.Repository.SessionRepository;
import com.example.sprintOneGrpThree.Repository.StaffRepository;

@Service
public class StaffServiceImpl implements StaffService{
	
	@Autowired
	private StaffRepository staffRepository;
	
	@Autowired
	private SessionRepository sessionRepository;

	@Override
	public String signUpStaff(Staff s) {
		// TODO Auto-generated method stub
		List<Staff> staffList = staffRepository.findAll();
		boolean res = false;
		if(!staffList.isEmpty()) {
		res = staffList.stream().anyMatch(n->n.getEmail().equals(s.getEmail()));
		}
		if(res) {
			return "Email id already exists";
		}
		else {
			staffRepository.save(s);
			sessionRepository.deleteAll();
			Session obj = new Session(s.getEmail(),"staff",s.getStaffId());
			sessionRepository.save(obj);
			return "SignUp successful !!";
		}

}

	@Override
	public String loginStaff(Staff s) {
		if(sessionRepository.count()==0) {
			return "Operation cannot be performed";
		}
		List<Staff> staffList = staffRepository.findAll();
		List<Staff> storeId = null;
		boolean res = false;
		if(!staffList.isEmpty()) {
		res = staffList.stream().filter(f->f.getEmail().equals(s.getEmail())).anyMatch(
				n->n.getPasscode().equals(s.getPasscode()));
		storeId = staffRepository.findAll().stream().filter(n->n.getEmail().equals(s.getEmail())).collect(
				Collectors.toList());
		}
		if(res) {
			Session obj = new Session(s.getEmail(),"staff",storeId.get(0).getStaffId());
			sessionRepository.deleteAll();
			sessionRepository.save(obj);
			return "login successful";
		}
		else {
			return "login failed";
		}
	}

	@Override
	public String updateStaff(Staff s) {
		if(sessionRepository.count()==0) {
			return "Update operation cannot be performed";
		}
		boolean res = sessionRepository.findAll().stream().anyMatch(n->n.getType().equals("customer"));
		if(res) {
			return "This operation cannot be performed by customer";
		}
		Session obj = sessionRepository.findAll().stream().findFirst().get();
	
		if(!s.getEmail().equals(obj.getEmail())) {
				return "Email cannot be udpated";
			}
	
		staffRepository.save(new Staff(obj.getId(),
				s.getName(),
				s.getType(),
				s.getEmail(),
				s.getPasscode(),
				s.getGender(),
				s.getMobile(),
				s.getHotels()));
			
		return "Your details are updated";
		}

	@Override
	public List<Staff> getStaffByEmail(String email) throws CustomerScopeViolationException {
		boolean res = sessionRepository.findAll().stream().anyMatch(n->n.getType().equals("customer"));
		if(res) {
			throw new CustomerScopeViolationException();
		}
		List<Staff> staffList = staffRepository.findByEmail(email);
		return staffList;
	}

	@Override
	public List<Staff> getAllStaff() throws CustomerScopeViolationException {
		boolean res = sessionRepository.findAll().stream().anyMatch(n->n.getType().equals("customer"));
		if(res) {
			throw new CustomerScopeViolationException();
		}
		List<Staff> staffList = staffRepository.findAll();
		return staffList;
	}

	@Override
	public String deleteStaff(String email) {
		boolean res = sessionRepository.findAll().stream().anyMatch(n->n.getType().equals("customer"));
		if(res) {
			return "This operation cannot be performed by customer";
		}
		List<Staff> s  = staffRepository.findByEmail(email);
		if(s.isEmpty()) {
			return "Staff with given email id not found";
		}
		
		
		staffRepository.deleteById(s.get(0).getStaffId());
		return "Staff record deleted";
	}
	
}
