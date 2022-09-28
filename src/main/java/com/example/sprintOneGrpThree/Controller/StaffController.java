package com.example.sprintOneGrpThree.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.sprintOneGrpThree.Entity.Hotel;
import com.example.sprintOneGrpThree.Entity.Staff;
import com.example.sprintOneGrpThree.Repository.StaffRepository;
import com.example.sprintOneGrpThree.Service.StaffService;

import java.util.List;
//student
@RestController
@RequestMapping("/staffs")
public class StaffController {

	@Autowired
	private StaffService staffService;
	
    @Autowired
    StaffRepository staffRepository;

    @GetMapping
    List<Staff> getStudents() {
        return staffRepository.findAll();
    }

    @PostMapping
    Staff createStudent(@RequestBody Staff staff) {
        return staffRepository.save(staff);
    }
    
    @DeleteMapping("/{id}")  
	public void deleteStaff(@PathVariable("id") int id)   
	{  
		staffService.delete(id);
	}
    
    @GetMapping("/id/{staffId}")
    Staff getStaffById(@PathVariable int staffId) {
        Staff staff = staffRepository.findById(staffId).get();
       return staff;
    }
}