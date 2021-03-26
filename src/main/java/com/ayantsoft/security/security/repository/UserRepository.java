package com.ayantsoft.security.security.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;



public interface UserRepository extends JpaRepository<User, Long>,Serializable{
			
	
	
	@Query("SELECT u FROM User u where u.userId = :userId") 
	User findByUserId(@Param("userId") String userId);
   
}
