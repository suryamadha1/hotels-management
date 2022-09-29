package com.example.sprintOneGrpThree.Service;

import java.util.List;
import java.util.Optional;

import com.example.sprintOneGrpThree.Entity.Room_desc;

public interface Room_descService {
	
	public Room_desc addRoomdesc(Room_desc roomdesc);
	
	public Optional<Room_desc> getRoomDescById(int room_id);
	
	public List<Room_desc> getRooms();

}
