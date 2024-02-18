package com.fabianbaldenegro.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fabianbaldenegro.app.entity.user;

@Repository
public interface UserRepository extends JpaRepository<user, Long>{
	
}
