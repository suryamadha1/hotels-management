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

import com.example.sprintOneGrpThree.Entity.Customer;
import com.example.sprintOneGrpThree.Entity.Hotel;
import com.example.sprintOneGrpThree.Entity.Rooms;
import com.example.sprintOneGrpThree.Entity.Session;
import com.example.sprintOneGrpThree.Entity.Transaction;
import com.example.sprintOneGrpThree.Exception.CustomerScopeViolationException;
import com.example.sprintOneGrpThree.Exception.InvalidOperationException;
import com.example.sprintOneGrpThree.Repository.CouponRepository;
import com.example.sprintOneGrpThree.Repository.CustomerRepository;
import com.example.sprintOneGrpThree.Repository.HotelRepository;
import com.example.sprintOneGrpThree.Repository.Room_descRepository;
import com.example.sprintOneGrpThree.Repository.RoomsRepository;
import com.example.sprintOneGrpThree.Repository.SessionRepository;
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
	@Autowired
	private SessionRepository sessionRepository;
	@Autowired
	private CouponRepository couponrepo;
	@Autowired
	private CustomerRepository customerRepository;
	
	@Override
	public Rooms addRoom(Rooms room) throws CustomerScopeViolationException {
//		if(room.getRoom_type())
		boolean res = sessionRepository.findAll().stream().anyMatch(n->n.getType().equals("staff"));
		if(!res) {
			throw new CustomerScopeViolationException();
		}
		Hotel h = hotelrepo.findById(room.getFk_hotel_id().getId()).get();
		room.setFk_hotel_id(h);
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
	
	public void addToTransactions(List<Integer> room_ids, Transaction transaction, int amount, Hotel hotelobj,double discamount) {
		
		
		
//		Hotel h = hotelrepo.findById(transaction.getHotel().getId()).get();
		
		
		Session obj = sessionRepository.findAll().stream().findFirst().get();
		Customer c = customerRepository.findById(obj.getId()).get();
		Transaction newtransaction = new Transaction();
		
		
		newtransaction.setAmount(amount);
		newtransaction.setCustomer(c);
		newtransaction.setHotel(hotelobj);
		newtransaction.setBooked_rooms_id(room_ids.toString());
		newtransaction.setCheck_in(transaction.getCheck_in());
		newtransaction.setCheck_out(transaction.getCheck_out());
//		newtransaction.setCustomer_id();   get customer id for session/login table
//		newtransaction.setHotel_id();		not added for cases when multiple hotels selected WIP 
		newtransaction.setNumber_of_people(transaction.getNumber_of_people());
		newtransaction.setPayment_mode(transaction.getPayment_mode()); 
		newtransaction.setTransaction_date(LocalDate.now());
		newtransaction.setCoupon_id(transaction.getCoupon_id());
		newtransaction.setDiscount_amount(discamount);
		transactionrepo.save(newtransaction);
	}

	
	@Override
	public Optional<List<Rooms>> bookRoomById(List<Integer> room_ids, Transaction transaction) throws InvalidOperationException {
		if(sessionRepository.count()==0) {
			throw new InvalidOperationException();
		}
		if (ValidRoomIdChecker(room_ids)==true) {
			Set<Hotel> hotel = new HashSet<>();
			int amount = 0;
			double discamount = 0;
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
						int room_price = roomsrepo.findById(id).get().getRoom_type().getRoom_price();
						amount += room_price;
						double coupon_amount = couponrepo.findById(transaction.getCoupon_id()).get().getAmount();
						double coupon_percentage = (roomsrepo.findById(id).get().getRoom_type().getRoom_price())/100;
						if(coupon_amount == 0)
							discamount += (room_price * coupon_percentage);
						else
							discamount += (room_price - coupon_amount);
				}
				Hotel newobj = roomsrepo.findById(room_ids.get(0)).get().getFk_hotel_id();
				System.out.println(hotel);
				addToTransactions(room_ids,transaction,amount,newobj,discamount);
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
