package com.prueba.tecnica.chakray.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prueba.tecnica.chakray.model.LoginRequest;
import com.prueba.tecnica.chakray.model.User;
import com.prueba.tecnica.chakray.repository.UserRepository;
import com.prueba.tecnica.chakray.service.Loginservice;
import com.prueba.tecnica.chakray.utils.AESUtil;

@Service
public class LoginServiceImpl implements Loginservice{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User login(LoginRequest loginRequest) {
		String decryptedPassword  = null;
		
		User user = userRepository.allUsers().stream()
	            .filter(u -> u.getTaxId().equals(loginRequest.getTaxId()))
	            .findFirst()
	            .orElseThrow(() -> new IllegalArgumentException("Invalid credentials"));

		try {

	        decryptedPassword = AESUtil.decrypt(user.getPassword());
	        
		} catch (Exception e) {
	    	e.printStackTrace();
	        throw new RuntimeException("Error decrypting password");
	    }
		
	        if (!loginRequest.getPassword().equals(decryptedPassword)) {
	            throw new IllegalArgumentException("Invalid credentials");
	        }

	    

	    return user;
	}

}
