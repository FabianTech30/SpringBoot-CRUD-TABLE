package com.fabianbaldenegro.app.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.fabianbaldenegro.app.entity.user;

public interface UserService {
	
	public Iterable<user> findAll();
	
	public Page<user> finAll(Pageable pageable);
	
	public Optional<user> findById(Long id);
	
	public user save(user user);
	
	public void deleteById(Long id);
}
