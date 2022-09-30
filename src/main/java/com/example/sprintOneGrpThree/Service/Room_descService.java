package com.example.sprintOneGrpThree.Service;

import java.util.List;
import java.util.Optional;

import com.example.sprintOneGrpThree.Entity.Room_desc;
import com.example.sprintOneGrpThree.Exception.CustomerScopeViolationException;

public interface Room_descService {
	
	public Room_desc addRoomdesc(Room_desc roomdesc) throws CustomerScopeViolationException;
	
	public Optional<Room_desc> getRoomDescById(int room_id);
	
	public List<Room_desc> getRooms();

}
