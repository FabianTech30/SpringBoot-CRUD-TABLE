package com.fabianbaldenegro.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fabianbaldenegro.app.entity.user;
import com.fabianbaldenegro.app.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	@Transactional(readOnly = true)
	public Iterable<user> findAll() {
		
		return userRepository.findAll();
	}

	@Override
	public Page<user> finAll(Pageable pageable) {
		
		return userRepository.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<user> findById(Long id) {
		
		return userRepository.findById(id);
	}

	@Override
	@Transactional
	public user save(user user) {
		
		return userRepository.save(user);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		userRepository.deleteById(id);
		
	}
	
}
