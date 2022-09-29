package com.example.sprintOneGrpThree.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sprintOneGrpThree.Entity.Hotel;
import com.example.sprintOneGrpThree.Repository.HotelRepository;

@Service
public class HotelSerivceImpl implements HotelService{
	@Autowired
	private HotelRepository hotelrepo;
	
	@Override
	public Hotel addHotel(Hotel hotel) {
		hotelrepo.save(hotel);
		return hotel;
	}
	
	

}
