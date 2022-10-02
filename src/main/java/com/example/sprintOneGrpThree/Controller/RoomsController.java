package com.example.sprintOneGrpThree.Controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.sprintOneGrpThree.Entity.Rooms;
import com.example.sprintOneGrpThree.Entity.Transaction;
import com.example.sprintOneGrpThree.Exception.CustomerScopeViolationException;
import com.example.sprintOneGrpThree.Exception.InvalidHotelIdException;
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
	
	@GetMapping({"/getRooms","/getRooms/{hotel_id}"})
	private ResponseEntity<List<Rooms>> getRooms(@PathVariable Optional<Integer> hotel_id) throws CustomerScopeViolationException,InvalidHotelIdException{
		if (hotel_id.isPresent()) {
			Optional<List<Rooms>> roomsObj = roomserv.getRoomsByHotelId(hotel_id.get());
				System.out.println(roomsObj.get());
				return new ResponseEntity<List<Rooms>> (roomsObj.get(), HttpStatus.OK);
		}
		else {
			List<Rooms> roomsObj = roomserv.getRooms();
			return new ResponseEntity<List<Rooms>> (roomsObj, HttpStatus.OK);
		}
		
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
	private ResponseEntity<String> unbookRoomById(@RequestParam List<Integer> room_ids){
		Optional<List<Rooms>> roomsObj = roomserv.unbookRoomById(room_ids);
		if(roomsObj==null) {
			System.out.println("ONE OR MORE OF THE SELECTED ROOM ID's ARE INVALID");
			return new ResponseEntity<String> ("ONE OR MORE OF THE SELECTED ROOM ID's ARE INVALID", HttpStatus.BAD_REQUEST);
		}
		else {
			System.out.println("UNBOOKED ROOM/S :" + roomsObj);
			return new ResponseEntity<String> ("UNBOOKED ROOM/S \n"+roomsObj.toString(), HttpStatus.OK);
		}
	}
	
	@GetMapping("/getUnbookedRooms")
	private ResponseEntity<String> getUnbookedRooms(){
		Optional<List<Rooms>> roomsObj = roomserv.getUnbookedRooms();
		if (roomsObj.isPresent()) {
			String str = "";
			for(Rooms room: roomsObj.get()) {
				str+=room+"\n";
			}
			System.out.println("AVAILABLE ROOMS ARE: " + str);
			return new ResponseEntity<String> ("AVAILABLE ROOMS ARE\n" + str, HttpStatus.OK);
		}
		else {
			System.out.println("ALL THE ROOMS ARE BOOKED");
			return new ResponseEntity<String> ("SORRY, ALL THE ROOMS ARE BOOKED RIGHT NOW", HttpStatus.OK);
		}	
	}
	
	@GetMapping("/getBookedRooms")
	private ResponseEntity<String> getBookedRooms() throws CustomerScopeViolationException{
		Optional<List<Rooms>> roomsObj = roomserv.getBookedRooms();
		if (roomsObj.isPresent()) {
			String str ="";
			for(Rooms room: roomsObj.get()) {
				str+=room+"\n";
			}
			System.out.println("BOOKED ROOMS ARE: " + str);
			return new ResponseEntity<String> ("BOOKED ROOMS ARE\n" + str, HttpStatus.OK);
		}
		else {
			System.out.println("ALL THE ROOMS ARE UNBOOKED");
			return new ResponseEntity<String> ("ALL THE ROOMS ARE UNBOOKED", HttpStatus.OK);
		}	
	}
	
	@GetMapping("/bookedRoomsStatus")
	private ResponseEntity<Map<Integer, String>> bookedRoomsStatus() throws CustomerScopeViolationException{
		Map<Integer, String> roomsObj = roomserv.bookedRoomsStatus();
		return new ResponseEntity<Map<Integer, String>> (roomsObj,HttpStatus.OK);
		
	}
	

	
	
}