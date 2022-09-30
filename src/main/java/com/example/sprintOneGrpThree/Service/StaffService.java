package com.example.sprintOneGrpThree.Service;

import java.util.List;

import com.example.sprintOneGrpThree.Entity.Staff;
import com.example.sprintOneGrpThree.Exception.CustomerScopeViolationException;

public interface StaffService {

	String signUpStaff(Staff s);

	String loginStaff(Staff c);

	String updateStaff(Staff s);

	List<Staff> getStaffByEmail(String email) throws CustomerScopeViolationException;

	List<Staff> getAllStaff() throws CustomerScopeViolationException;

	String deleteStaff(String email);

}
