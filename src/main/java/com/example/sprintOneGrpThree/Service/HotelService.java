package com.example.sprintOneGrpThree.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.sprintOneGrpThree.Entity.Hotel;

@Service
public interface HotelService{

	public List<Hotel> getHotelByName(String name);

	public List<Hotel> getAllHotels();

	public Optional<Hotel> getHotelById(int id);

}