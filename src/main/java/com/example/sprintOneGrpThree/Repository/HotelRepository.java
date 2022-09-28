package com.example.sprintOneGrpThree.Repository;

import javax.transaction.Transactional;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.sprintOneGrpThree.Entity.Hotel;

@Repository
@Transactional
public interface HotelRepository extends JpaRepository<Hotel, Integer> {
	List<Hotel> findByName(String name);
}
