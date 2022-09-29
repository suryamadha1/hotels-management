package com.example.sprintOneGrpThree.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import com.example.sprintOneGrpThree.Entity.Rooms;
import com.example.sprintOneGrpThree.Entity.Transaction;

public interface RoomsService {
	public Rooms addRoom(Rooms room);
	
	public List<Rooms> getRooms();
	
	public Optional<List<Rooms>> bookRoomById(List<Integer> room_ids, Transaction transaction);
	
	public Optional<List<Rooms>> unbookRoomById(List<Integer> room_ids);

}
