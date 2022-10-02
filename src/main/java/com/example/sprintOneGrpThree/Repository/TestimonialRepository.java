package com.example.sprintOneGrpThree.Repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.sprintOneGrpThree.Entity.Customer;
import com.example.sprintOneGrpThree.Entity.Testimonial;

@Transactional
@Repository
public interface TestimonialRepository extends JpaRepository<Testimonial, Integer>{
	
	@Query("select t from Testimonial t where t.rating >=:r")
	public List<Testimonial> findByRatingCondition(@Param("r") int rating);

}
