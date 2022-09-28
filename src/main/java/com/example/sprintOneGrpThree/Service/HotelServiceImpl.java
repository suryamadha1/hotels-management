package com.example.sprintOneGrpThree.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.sprintOneGrpThree.Entity.Hotel;
import com.example.sprintOneGrpThree.Repository.HotelRepository;

@Service
public class HotelServiceImpl implements HotelService{

	@Autowired
	private HotelRepository hotelRepository;
	
	@Override
	public List<Hotel> getHotelByName(String name) {
		List<Hotel> hotelList = hotelRepository.findByName(name);
		return hotelList;
	}

	@Override
	public List<Hotel> getAllHotels() {
		List<Hotel> hotelList = hotelRepository.findAll();
		return hotelList;
	}

	@Override
	public Optional<Hotel> getHotelById(int id) {
		  Optional<Hotel> hotel = hotelRepository.findById(id);
			return hotel;
	}

}
