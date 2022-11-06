package com.image.controllers;

import org.springframework.beans.factory.annotation.Autowired;
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
	public String registerUser(@RequestBody User user)
	{
		usi.registerNewUser(user);
		return null;
	}

}
