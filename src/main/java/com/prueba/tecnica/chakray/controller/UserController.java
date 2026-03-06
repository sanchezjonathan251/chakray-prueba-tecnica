package com.prueba.tecnica.chakray.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.tecnica.chakray.model.User;
import com.prueba.tecnica.chakray.service.UserService;

@RestController
@RequestMapping("/")
public class UserController {

	@Autowired
	private UserService userService;
	
	
	@GetMapping("users/h2")
	public ResponseEntity<?> usersSortedH2(@RequestParam(required = false) String sortBy){
		List<User> users = null;
		
		try {
			users = userService.allUsersSortedH2(sortBy);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                .body(e.getMessage());
		}
		
		return ResponseEntity.ok(users);
	}
	
	@GetMapping(value = "users", params = "sortBy")
	public ResponseEntity<?> usersSorted(@RequestParam String sortBy){
		List<User> users = null;
		
		try {
			users = userService.allUsersSortedArray(sortBy);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                .body(e.getMessage());
		}
		
		return ResponseEntity.ok(users);
	}
	
	@GetMapping(value = "users", params = "filter")
	public ResponseEntity<?> usersfilter(@RequestParam String filter){
		List<User> users = null;
		
		try {
			users = userService.usersFilter(filter);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                .body(e.getMessage());
		}
		
		return ResponseEntity.ok(users);
	}
	
	
}
