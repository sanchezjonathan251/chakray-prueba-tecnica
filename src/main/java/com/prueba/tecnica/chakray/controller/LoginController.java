package com.prueba.tecnica.chakray.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.tecnica.chakray.model.LoginRequest;
import com.prueba.tecnica.chakray.model.User;
import com.prueba.tecnica.chakray.service.Loginservice;

@RestController
@RequestMapping("/api")
public class LoginController {
	@Autowired
	private Loginservice loginService;
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest request){

	    try {

	        User user = loginService.login(request);

	        return ResponseEntity.ok(user);

	    } catch (IllegalArgumentException e) {

	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
	                .body(e.getMessage());

	    }catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body(e.getMessage());
		}
	}
}
