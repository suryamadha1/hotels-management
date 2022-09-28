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

@RestController
public class HotelController {
	
	@Autowired
	private HotelService hotelService;
	
	@PostMapping("/AddHotel")
	public ResponseEntity<String> addHotel(@RequestBody Hotel h) {
		String response = hotelService.addHotel(h);
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}

}
