package com.example.sprintOneGrpThree.Controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.sprintOneGrpThree.Entity.Rooms;
import com.example.sprintOneGrpThree.Entity.Transaction;
import com.example.sprintOneGrpThree.Exception.CustomerScopeViolationException;
import com.example.sprintOneGrpThree.Exception.InvalidOperationException;
import com.example.sprintOneGrpThree.Service.RoomsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class RoomsController {
	
	@Autowired
	private RoomsService roomserv;
	
	@PostMapping("/addRoom")
	@ResponseBody
	private ResponseEntity<Rooms> addRoom(@RequestBody Rooms room) throws CustomerScopeViolationException{
		Rooms roomsObj = roomserv.addRoom(room);
		return new ResponseEntity<Rooms> (roomsObj, HttpStatus.OK);
		
	}
	
	@GetMapping("/getRooms")
	private ResponseEntity<List<Rooms>> getRooms(){
		List<Rooms> roomsObj = roomserv.getRooms();
		return new ResponseEntity<List<Rooms>> (roomsObj, HttpStatus.OK);
		
	}
	
	@GetMapping(value="/bookRoomById",params="room_ids")
	@ResponseBody
	private ResponseEntity<String> bookRoomById(@RequestParam List<Integer> room_ids, @RequestBody Transaction transaction) throws InvalidOperationException{
		Optional<List<Rooms>> roomsObj = roomserv.bookRoomById(room_ids,transaction);
		if(roomsObj==null) {
			System.out.println("ONE OR MORE OF THE SELECTED ROOM ID's ARE INVALID");
			return new ResponseEntity<String> ("ONE OR MORE OF THE SELECTED ROOM ID's ARE INVALID", HttpStatus.BAD_REQUEST);
			
		}
		else if(roomsObj.get().size()==room_ids.size()) {
			System.out.println("BOOKED ROOMS are:" + roomsObj);
			return new ResponseEntity<String> ("BOOKED ROOM/S "+roomsObj.get().toString(), HttpStatus.OK);
		}
		else if(roomsObj.get().size()==0){
			System.out.println("SELECTED ROOMS ARE NOT AVAILABLE");
			return new ResponseEntity<String> ("SELECTED ROOMS ARE NOT AVAILABLE",HttpStatus.BAD_REQUEST);
		}
		else{
			System.out.println("AVAILABLE ROOMS are:" + roomsObj);
			return new ResponseEntity<String> ("SOME OF THE SELECTED ROOMS ARE NOT AVAILABLE \n AVAILABLE ROOMS ARE:"+roomsObj.get().toString(), HttpStatus.BAD_REQUEST);
		}
		
		
		
	}
	
	@GetMapping(value="/checkout",params="room_ids")
	private ResponseEntity<Optional<List<Rooms>>> unbookRoomById(@RequestParam List<Integer> room_ids){
		Optional<List<Rooms>> roomsObj = roomserv.unbookRoomById(room_ids);
		System.out.println("BOOKED ROOMS are:" + roomsObj);
		return new ResponseEntity<Optional<List<Rooms>>> (roomsObj, HttpStatus.OK);
		
	}
}