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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api")
@Tag(name = "Login", description = "login API")
public class LoginController {
	@Autowired
	private Loginservice loginService;
	
	@PostMapping("/login")
	@Operation(summary = "Login username is tax_id ")
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
