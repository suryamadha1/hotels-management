package com.example.sprintOneGrpThree.Repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.sprintOneGrpThree.Entity.Room_desc;

@Transactional
@Repository
public interface Room_descRepository extends JpaRepository<Room_desc,Integer>{
	
	@Query("SELECT r FROM Room_desc r WHERE r.type = :type")
	public Room_desc findByType(@Param("type") String string);

}