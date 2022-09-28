package com.example.sprintOneGrpThree.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sprintOneGrpThree.Entity.Hotel;
import com.example.sprintOneGrpThree.Repository.HotelRepository;

@Service
public class HotelServiceImpl implements HotelService{
	
	@Autowired
	private HotelRepository hotelRepository;

	@Override
	public String addHotel(Hotel h) {
		// TODO Auto-generated method stub
		hotelRepository.save(h);
		return "hotel saved";
	}

}
