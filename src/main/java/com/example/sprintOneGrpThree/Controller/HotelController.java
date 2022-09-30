package com.example.sprintOneGrpThree.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.sprintOneGrpThree.Entity.Customer;
import com.example.sprintOneGrpThree.Entity.Hotel;
import com.example.sprintOneGrpThree.Service.HotelService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.sprintOneGrpThree.Entity.Enquiry;
import com.example.sprintOneGrpThree.Entity.Hotel;
import com.example.sprintOneGrpThree.Entity.Staff;
import com.example.sprintOneGrpThree.Exception.CustomerScopeViolationException;
import com.example.sprintOneGrpThree.Repository.EnquiryRepository;
import com.example.sprintOneGrpThree.Repository.HotelRepository;
import com.example.sprintOneGrpThree.Repository.SessionRepository;
import com.example.sprintOneGrpThree.Repository.StaffRepository;
import com.example.sprintOneGrpThree.Service.HotelService;

import java.util.List;
import java.util.Optional;
//subject
@RestController
@RequestMapping("/hotels")
public class HotelController {
	
	@Autowired
	private HotelService hotelService;

    @Autowired
    HotelRepository hotelRepository;

    @Autowired
    StaffRepository staffRepository;
    
    @Autowired
    EnquiryRepository enquiryRepository;
    
    @Autowired
    SessionRepository sessionRepository;

    
    @PostMapping
    Hotel createHotel(@RequestBody Hotel hotel) throws CustomerScopeViolationException {
    	boolean res = sessionRepository.findAll().stream().anyMatch(n->n.getType().equals("staff"));
		if(!res) {
			throw new CustomerScopeViolationException();
		}
        return hotelRepository.save(hotel);
    }

    @PutMapping("/{hotelId}/staffs/{staffId}")
    Hotel addStaffToHotel(
            @PathVariable int hotelId,
            @PathVariable int staffId
    ) {
        Hotel hotel = hotelRepository.findById(hotelId).get();
        Staff staff = staffRepository.findById(staffId).get();
        hotel.enrolledStaffs.add(staff);
        return hotelRepository.save(hotel);
    }
    
    @PutMapping("/{hotelId}/enquiry/{enquiryId}")
    Hotel assignEnquiryToHotel(
            @PathVariable int hotelId,
            @PathVariable int enquiryId
    ) {
        Hotel hotel = hotelRepository.findById(hotelId).get();
        Enquiry enquiry = enquiryRepository.findById(enquiryId).get();
        hotel.setEnquiry(enquiry);
        return hotelRepository.save(hotel);
    }
    

	@GetMapping("/id/{id}")
	public ResponseEntity<Optional<Hotel>> getHotelById(@PathVariable int id){
		Optional<Hotel> hotel = hotelService.getHotelById(id);
		return new ResponseEntity<Optional<Hotel>>(hotel,HttpStatus.OK); 
	}
    
    
    @GetMapping("/name/{name}")
	public ResponseEntity<Object> getHotelByName(@PathVariable("name") String name){
		List<Hotel> hotel = hotelService.getHotelByName(name);
		if(!hotel.isEmpty())
			return new ResponseEntity<>(hotel, HttpStatus.OK);
		else
			return new ResponseEntity<>("Hotel with given name not found",HttpStatus.NOT_FOUND);
		
	}
	
	@GetMapping
	public ResponseEntity<Object> getAllHotels(){
		List<Hotel> hotels = hotelService.getAllHotels();
		return new ResponseEntity<>(hotels,HttpStatus.OK);
		
	}
	

}