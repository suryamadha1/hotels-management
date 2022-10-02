package com.example.sprintOneGrpThree.Repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.sprintOneGrpThree.Entity.Session;

@Transactional
@Repository
public interface SessionRepository extends JpaRepository<Session, Integer>{

}
