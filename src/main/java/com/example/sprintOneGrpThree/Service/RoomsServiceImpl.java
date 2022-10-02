package com.example.sprintOneGrpThree.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.sprintOneGrpThree.Entity.Coupon;
import com.example.sprintOneGrpThree.Entity.Customer;
import com.example.sprintOneGrpThree.Entity.Hotel;
import com.example.sprintOneGrpThree.Entity.Room_desc;
import com.example.sprintOneGrpThree.Entity.Rooms;
import com.example.sprintOneGrpThree.Entity.Session;
import com.example.sprintOneGrpThree.Entity.Transaction;
import com.example.sprintOneGrpThree.Exception.CustomerScopeViolationException;
import com.example.sprintOneGrpThree.Exception.InvalidHotelIdException;
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
		if(!res) 
			throw new CustomerScopeViolationException();
		Hotel h = hotelrepo.findById(room.getFk_hotel_id().getId()).get();
		Room_desc roomdesc = room_descrepo.findByType(room.getRoom_type().getType());
		room.setFk_hotel_id(h);
		room.setRoom_type(roomdesc);
		System.out.println(room);
		Rooms roomsObj = roomsrepo.save(room);
		return roomsObj;
	}
	
	@Override
	public List<Rooms> getRooms() throws CustomerScopeViolationException{
		boolean res = sessionRepository.findAll().stream().anyMatch(n->n.getType().equals("staff"));
		if(!res) {
			throw new CustomerScopeViolationException();
		}
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
			// MAYBE THROW A EMPTY LIST EXCEPTION HERE
		if(count==0)
			return true;
		else
			return false;		
	}
	
	public List<Rooms> RoomAvaibilityChecker(List<Integer> room_ids) {
		List<Rooms> temp = roomsrepo.findAllById(room_ids);
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
	
	public void addToTransactions(List<Integer> room_ids, Transaction transaction, int amount, Hotel hotelobj) {
		
		Session obj = sessionRepository.findAll().stream().findFirst().get();
		Customer c = customerRepository.findById(obj.getId()).get();
		Transaction newtransaction = new Transaction();
		double coupon_amount = couponrepo.findById(transaction.getCoupon_id()).get().getAmount();
		float coupon_percentage = (float) ((couponrepo.findById(transaction.getCoupon_id()).get().getPercentage())/100.0);
		float discamount = 0;
		if(coupon_percentage != 0) 
			discamount = amount - (amount * coupon_percentage);
		else
			discamount = (float) (amount - coupon_amount);
		newtransaction.setAmount(amount);
		newtransaction.setCustomer(c);
		newtransaction.setHotel(hotelobj);
		newtransaction.setBooked_rooms_id(room_ids.toString());
		newtransaction.setCheck_in(transaction.getCheck_in());
		newtransaction.setCheck_out(transaction.getCheck_out());
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

				}
				Hotel newobj = roomsrepo.findById(room_ids.get(0)).get().getFk_hotel_id();
				System.out.println(hotel);
				addToTransactions(room_ids,transaction,amount,newobj);
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
		if (ValidRoomIdChecker(room_ids)==true) {
			List<Rooms> temp = roomsrepo.findAllById(room_ids);
			List<Rooms> bookedroomlist = new ArrayList<>();
			System.out.println(room_ids);
			if (temp.size()>0) {
				for(int id: room_ids) {
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
			}
			return Optional.ofNullable(bookedroomlist);
		}
		else {
			//INVALID ROOM ID GIVEN
			return null;
		}
	}

	@Override
	public Optional<List<Rooms>> getUnbookedRooms() {
		return Optional.ofNullable(roomsrepo.findAllByBookedstatus(false));
		//return Optional.ofNullable(roomsrepo.findAll().stream().filter(x->x.isBooked_status()==false).collect(Collectors.toList()));
	}

	@Override
	public Optional<List<Rooms>> getBookedRooms() throws CustomerScopeViolationException{
		boolean res = sessionRepository.findAll().stream().anyMatch(n->n.getType().equals("staff"));
		if(!res) {
			throw new CustomerScopeViolationException();
		}
		return Optional.ofNullable(roomsrepo.findAllByBookedstatus(true));
		//return Optional.ofNullable(roomsrepo.findAll().stream().filter(x->x.isBooked_status()==true).collect(Collectors.toList()));
	}

	@Override
	public Map<Integer, String> bookedRoomsStatus() throws CustomerScopeViolationException{
		boolean res = sessionRepository.findAll().stream().anyMatch(n->n.getType().equals("staff"));
		if(!res) {
			throw new CustomerScopeViolationException();
		}
		Map<Integer, String> result = new HashMap<>();
		Optional<List<Rooms>> Bookedrooms = getBookedRooms();
		List<Transaction> All_transactions = transactionrepo.findAll(Sort.by(Sort.Direction.DESC,"id"));
		for(Rooms room: Bookedrooms.get()) {
			System.out.println(room.getRoom_no()+"  "+room.isBooked_status());
			for(Transaction transaction:All_transactions) {
				String temp = transaction.getBooked_rooms_id();
				temp = temp.substring(1,temp.length()-1);
				temp = temp.replace(" ", "");
				String[] strsplit = temp.split("[,]",0);
				int found = 0;
			       for(String myStr: strsplit) {
                      if ( Integer.valueOf(myStr) == room.getId()) {
                    	  String str="Customer_name="+ transaction.getCustomer().getName() +
                    			  ", Hotel_name=" + room.getFk_hotel_id().getName() + 
                    			  ", Room_no =" + room.getRoom_no() +
                    	  		  ", Checkout_date=" + transaction.getCheck_out() +
                    	  		  ", Customer_contact=" + transaction.getCustomer().getMobile();
                    	  result.put(result.size(),str);
                    	  found+=1;
                      }
			        }
			    if ( found == strsplit.length )
			    	break;
			}
		}
		for (Map.Entry<Integer, String> pair : result.entrySet()) {
		    System.out.println(String.format("%s: %s", pair.getKey(), pair.getValue()));   
		}
		return result;
	}

	@Override
	public Optional<List<Rooms>> getRoomsByHotelId(int id) throws InvalidHotelIdException,CustomerScopeViolationException{
		boolean res = sessionRepository.findAll().stream().anyMatch(n->n.getType().equals("staff"));
		if(!res)
			throw new CustomerScopeViolationException();
		Optional<List<Rooms>> roomsObj = Optional.ofNullable(roomsrepo.findAllByfk_hotel_id(id));
		if (roomsObj.get().isEmpty()) {
			System.out.println("INVALID HOTEL ID");
			throw new InvalidHotelIdException();
		}
		else 
			return roomsObj;
		
	}

}