package com.image.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.image.entities.User;
import com.image.services.UserServiceImplementation;

@RestController
public class UserController {
	
	
	@Autowired
	private UserServiceImplementation usi;
	
	@PostMapping("/register/user")
	public ResponseEntity<?> registerUser(@RequestBody User user)
	{
		User registerNewUser = usi.registerNewUser(user);
		return new ResponseEntity<>("User Created.", HttpStatus.CREATED);
	}

}
