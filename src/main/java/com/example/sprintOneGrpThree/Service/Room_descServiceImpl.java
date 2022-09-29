package com.example.sprintOneGrpThree.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sprintOneGrpThree.Entity.Room_desc;
import com.example.sprintOneGrpThree.Repository.Room_descRepository;

@Service
public class Room_descServiceImpl implements Room_descService{

	@Autowired
	private Room_descRepository roomdescrepo;
	
	@Override
	public Room_desc addRoomdesc(Room_desc roomdesc) {
		Room_desc roomdescObj = roomdescrepo.save(roomdesc);
		return roomdescObj;
	}

	@Override
	public Optional<Room_desc> getRoomDescById(int room_id) {
		Optional<Room_desc> roomdescObj = roomdescrepo.findById(room_id);
		return roomdescObj;
	}

	@Override
	public List<Room_desc> getRooms() {
		return roomdescrepo.findAll();
	}

}
