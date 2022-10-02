package com.example.sprintOneGrpThree.Repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.sprintOneGrpThree.Entity.Rooms;

@Transactional
@Repository
public interface RoomsRepository extends JpaRepository<Rooms,Integer> {

	@Query("SELECT r FROM Rooms r WHERE r.booked_status = :type")
	public List<Rooms> findAllByBookedstatus(@Param("type") Boolean value);
	
	@Query("SELECT r FROM Rooms r WHERE r.fk_hotel_id.id = :id")
	public List<Rooms> findAllByfk_hotel_id(@Param("id") int value);
}

