package com.image.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.image.entities.User;
import com.image.repositories.UserRepository;

@Service
public class UserServiceImplementation {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private PasswordEncoder passEn;

	public User registerNewUser(User user) {
		
		String encode = passEn.encode(user.getPassword());
		user.setPassword(encode);
		user.setAuthority("USER");
		User returnedUser = userRepo.save(user);
		
		return returnedUser;
	}

}
