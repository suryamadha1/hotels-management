package com.example.sprintOneGrpThree.Service;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.example.sprintOneGrpThree.Entity.Rooms;
import com.example.sprintOneGrpThree.Entity.Transaction;
import com.example.sprintOneGrpThree.Exception.CustomerScopeViolationException;
import com.example.sprintOneGrpThree.Exception.InvalidHotelIdException;
import com.example.sprintOneGrpThree.Exception.InvalidOperationException;

public interface RoomsService {
	public Rooms addRoom(Rooms room) throws CustomerScopeViolationException;
	
	public List<Rooms> getRooms() throws CustomerScopeViolationException;
	
	public Optional<List<Rooms>> bookRoomById(List<Integer> room_ids, Transaction transaction) throws InvalidOperationException;
	
	public Optional<List<Rooms>> unbookRoomById(List<Integer> room_ids);
	
	public Optional<List<Rooms>> getUnbookedRooms();
	
	public Optional<List<Rooms>> getBookedRooms() throws CustomerScopeViolationException;
	
	public Map<Integer, String> bookedRoomsStatus() throws CustomerScopeViolationException;
	
	public Optional<List<Rooms>> getRoomsByHotelId(int id) throws InvalidHotelIdException,CustomerScopeViolationException;

}
