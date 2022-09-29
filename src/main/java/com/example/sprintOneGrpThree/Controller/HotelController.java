package com.example.sprintOneGrpThree.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.sprintOneGrpThree.Entity.Hotel;
import com.example.sprintOneGrpThree.Service.HotelService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class HotelController {
	
	@Autowired
	private HotelService hotelserv;
	
	@PostMapping("/addHotel")
	@ResponseBody
	private ResponseEntity<Hotel> addHotel(@RequestBody Hotel hotel){
		System.out.println(hotel);
		Hotel obj = hotelserv.addHotel(hotel);
		return new ResponseEntity<Hotel> (obj,HttpStatus.OK);
		
	}
}
