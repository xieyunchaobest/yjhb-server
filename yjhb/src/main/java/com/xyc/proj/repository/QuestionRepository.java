package com.xyc.proj.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.xyc.proj.entity.Question;
import com.xyc.proj.entity.User;



public interface QuestionRepository extends JpaRepository<Question, Long> {

	 
}
