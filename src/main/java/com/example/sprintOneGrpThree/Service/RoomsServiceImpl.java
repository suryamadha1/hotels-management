package com.example.sprintOneGrpThree.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sprintOneGrpThree.Entity.Hotel;
import com.example.sprintOneGrpThree.Entity.Rooms;
import com.example.sprintOneGrpThree.Entity.Transaction;
import com.example.sprintOneGrpThree.Repository.HotelRepository;
import com.example.sprintOneGrpThree.Repository.Room_descRepository;
import com.example.sprintOneGrpThree.Repository.RoomsRepository;
import com.example.sprintOneGrpThree.Repository.TransactionRepository;

@Service
public class RoomsServiceImpl implements RoomsService{
	
	@Autowired
	private RoomsRepository roomsrepo;
	@Autowired
	private HotelRepository hotelrepo;
	@Autowired
	private TransactionRepository transactionrepo;
	@Autowired
	private Room_descRepository room_descrepo;
	
	@Override
	public Rooms addRoom(Rooms room) {
//		if(room.getRoom_type())
		Rooms roomsObj = roomsrepo.save(room);
		return roomsObj;
	}
	
	@Override
	public List<Rooms> getRooms() {
		return roomsrepo.findAll();
	}
	
	public boolean ValidRoomIdChecker(List<Integer> room_ids) {
		List<Rooms> temp = roomsrepo.findAllById(room_ids);
		int count = 0;
		if (temp.size()>0) {
			for(int id: room_ids) {
				if(roomsrepo.findById(id).isPresent()==false) {
					count+=1;
					break;
				}
			}
		}
		else 
			count=-1;
			// MAYBE THROW A EMPTY LIST ERROR HERE
		if(count==0)
			return true;
		else
			return false;		
	}
	
	public List<Rooms> RoomAvaibilityChecker(List<Integer> room_ids) {
		List<Rooms> temp = roomsrepo.findAllById(room_ids);
		int count = 0;
		List<Rooms> availableRooms = new ArrayList<>();
		if (temp.size()>0) {
			for(int id: room_ids) {
					if(roomsrepo.findById(id).get().isBooked_status()==false) {
						availableRooms.add(roomsrepo.findById(id).get());
					}			
				}
			}
		return availableRooms;
	}
	
	public void addToTransactions(List<Integer> room_ids, Transaction transaction, int amount) {
		
		Transaction newtransaction = new Transaction();
		newtransaction.setAmount(amount);
		newtransaction.setBooked_rooms_id(room_ids.toString());
		newtransaction.setCheck_in(transaction.getCheck_in());
		newtransaction.setCheck_out(transaction.getCheck_out());
//		newtransaction.setCustomer_id();   get customer id for session/login table
//		newtransaction.setHotel_id();		not added for cases when multiple hotels selected WIP 
		newtransaction.setNumber_of_people(transaction.getNumber_of_people());
		newtransaction.setPayment_mode(transaction.getPayment_mode()); 
		newtransaction.setTransaction_date(LocalDate.now());
		transactionrepo.save(newtransaction);
	}

	
	@Override
	public Optional<List<Rooms>> bookRoomById(List<Integer> room_ids, Transaction transaction) {
		if (ValidRoomIdChecker(room_ids)==true) {
			Set<Hotel> hotel = new HashSet<>();
			int amount = 0;
			List<Rooms> available_rooms = RoomAvaibilityChecker(room_ids);
			List<Rooms> bookedroomlist = new ArrayList<>();
			if(available_rooms.size()==room_ids.size()) {
				for(int id: room_ids) {
						roomsrepo.findById(id).get().setBooked_status(true);
						bookedroomlist.add(roomsrepo.findById(id).get());
						roomsrepo.save(roomsrepo.findById(id).get());
						System.out.println("Room "+roomsrepo.findById(id).get().getId()+" booked");
						hotel.add(roomsrepo.findById(id).get().getFk_hotel_id());
						//roomsrepo.findById(id).get().getRoom_type()    //Custom querry to get by room_type and add final amount for all the rooms
						System.out.println("room type:" +roomsrepo.findById(id).get().getRoom_type().getRoom_price());
						amount +=roomsrepo.findById(id).get().getRoom_type().getRoom_price();
				}
				System.out.println(hotel);
				addToTransactions(room_ids,transaction,amount);
				System.out.println(room_ids);
				return Optional.ofNullable(bookedroomlist);
			}
			else {
				System.out.println(room_ids);
				System.out.println("SOME OF THE ROOMS ARE UNAVAILABLE, available rooms are \n" + available_rooms);
				return Optional.ofNullable(available_rooms);
			}
			
			
		}
		else {
			//INVALID ROOM ID GIVEN
			return null;
		}
	}
	
	@Override
	public Optional<List<Rooms>> unbookRoomById(List<Integer> room_ids) {
		List<Rooms> temp = roomsrepo.findAllById(room_ids);
		List<Rooms> bookedroomlist = new ArrayList<>();
		System.out.println(room_ids);
		if (temp.size()>0) {
			for(int id: room_ids) {
				System.out.println(roomsrepo.findById(id).isPresent());
				if(roomsrepo.findById(id).isPresent()==true) {
					if(roomsrepo.findById(id).get().isBooked_status()==true) {
						roomsrepo.findById(id).get().setBooked_status(false);
						bookedroomlist.add(roomsrepo.findById(id).get());
						roomsrepo.save(roomsrepo.findById(id).get());
						System.out.println("Room "+roomsrepo.findById(id).get().getId()+" unbooked");
						
					}
					else {
						System.out.println("Room "+roomsrepo.findById(id).get().getId()+" is not booked");
					}
				}
				else {
					System.out.println("Room with id "+ id +" does not exists");
				}
				
			}
		}
		
		return Optional.ofNullable(bookedroomlist);
	}

}
