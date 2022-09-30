package com.example.sprintOneGrpThree.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.sprintOneGrpThree.Entity.Room_desc;
import com.example.sprintOneGrpThree.Exception.CustomerScopeViolationException;
import com.example.sprintOneGrpThree.Service.Room_descService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class Room_descController {

	@Autowired
	private Room_descService roomdescServ;
	
	@PostMapping("/addRoomdesc")
	@ResponseBody
	private ResponseEntity<Room_desc> addroomdesc(@RequestBody Room_desc roomdesc) throws CustomerScopeViolationException{
		Room_desc roomdescObj = roomdescServ.addRoomdesc(roomdesc);
		return new ResponseEntity<Room_desc> (roomdescObj,HttpStatus.OK);
		
	}
	
	@GetMapping("/getRoomDescById/{room_id}")
	private ResponseEntity<String> getRoomDescById(@PathVariable int room_id){
		Optional<Room_desc> roomdescObj = roomdescServ.getRoomDescById(room_id);
		if (roomdescObj.isPresent())
			return new ResponseEntity<String> (roomdescObj.toString(),HttpStatus.OK);
		else
			return new ResponseEntity<String> ("NOT FOUND, also make a exception for this part",HttpStatus.OK);
	}
	
	@GetMapping("/getRoomsDesc")
	private ResponseEntity<List<Room_desc>> getRooms(@PathVariable int room_id){
		return new ResponseEntity<List<Room_desc>> (roomdescServ.getRooms(),HttpStatus.OK);
		
	}
	
//	@GetMapping({"/getRooms","/getRooms/{hotel_id}"})
//	private ResponseEntity<List<Room_desc>> getRoomDesc(@PathVariable Optional<Integer> hotel_id){
//		roomdescServ.getRoomDesc(hotel_id);
//		return null;
//		
//	}
	
}
