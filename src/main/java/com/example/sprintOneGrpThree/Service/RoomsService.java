package com.example.sprintOneGrpThree.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import com.example.sprintOneGrpThree.Entity.Rooms;
import com.example.sprintOneGrpThree.Entity.Transaction;
import com.example.sprintOneGrpThree.Exception.CustomerScopeViolationException;
import com.example.sprintOneGrpThree.Exception.InvalidOperationException;

public interface RoomsService {
	public Rooms addRoom(Rooms room) throws CustomerScopeViolationException;
	
	public List<Rooms> getRooms();
	
	public Optional<List<Rooms>> bookRoomById(List<Integer> room_ids, Transaction transaction) throws InvalidOperationException;
	
	public Optional<List<Rooms>> unbookRoomById(List<Integer> room_ids);

}
