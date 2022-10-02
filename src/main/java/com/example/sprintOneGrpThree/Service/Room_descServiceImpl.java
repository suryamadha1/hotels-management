package com.example.sprintOneGrpThree.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sprintOneGrpThree.Entity.Room_desc;
import com.example.sprintOneGrpThree.Exception.CustomerScopeViolationException;
import com.example.sprintOneGrpThree.Repository.Room_descRepository;
import com.example.sprintOneGrpThree.Repository.SessionRepository;

@Service
public class Room_descServiceImpl implements Room_descService{

	@Autowired
	private Room_descRepository roomdescrepo;
	
	@Autowired
	private SessionRepository sessionRepository;
	
	@Override
	public Room_desc addRoomdesc(Room_desc roomdesc) throws CustomerScopeViolationException {
		boolean res = sessionRepository.findAll().stream().anyMatch(n->n.getType().equals("staff"));
		if(!res) {
			throw new CustomerScopeViolationException();
		}
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
