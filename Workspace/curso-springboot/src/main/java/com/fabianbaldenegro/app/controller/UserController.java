package com.fabianbaldenegro.app.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fabianbaldenegro.app.entity.user;
import com.fabianbaldenegro.app.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	// Create a new user
	@PostMapping
	public ResponseEntity<?> create(@RequestBody user user){
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
	}
	
	//Read an user
	@GetMapping("/{id}")
	public ResponseEntity<?> read(@PathVariable(value = "id") Long userId){
		Optional<user>oUser = userService.findById(userId);
		
		if(!oUser.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(oUser);
	}
	
	//Update an user
	@PutMapping("/{id}")
	public ResponseEntity<?> update (@RequestBody user userDetails, @PathVariable(value = "id") Long userId){
		Optional<user> user = userService.findById(userId);
		
		if(!user.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		user.get().setName(userDetails.getName());
		user.get().setSurname(userDetails.getSurname());
		user.get().setEmail(userDetails.getEmail());
		user.get().setEnable(userDetails.getEnable());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user.get()));
	}
	
	//Delete an user
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete (@PathVariable(value = "id") Long userId){
		
		if(!userService.findById(userId).isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		userService.deleteById(userId);
		return ResponseEntity.ok().build();
	}
	
	//Read all users
	@GetMapping
	public List<user> readAll (){
		
		List<user> users = StreamSupport
				.stream(userService.findAll().spliterator(), false)
				.collect(Collectors.toList());
		
		return users;	
	}
}
