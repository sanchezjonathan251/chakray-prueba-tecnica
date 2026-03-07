package com.prueba.tecnica.chakray.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.tecnica.chakray.model.User;
import com.prueba.tecnica.chakray.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/")
public class UserController {

	@Autowired
	private UserService userService;
	
	
	@GetMapping("users/h2")
	public ResponseEntity<?> usersSortedH2(@RequestParam(required = false) String sortBy){
		try {
			List<User> users = userService.allUsersSortedH2(sortBy);
			return ResponseEntity.ok(users);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                .body(e.getMessage());
		}
		
	}
	
	@GetMapping(value = "users", params = "sortBy")
	public ResponseEntity<?> usersSorted(@RequestParam String sortBy){
		try {
			List<User> users = userService.allUsersSortedArray(sortBy);
			return ResponseEntity.ok(users);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                .body(e.getMessage());
		}
		
	}
	
	@GetMapping(value = "users", params = "filter")
	public ResponseEntity<?> usersfilter(@RequestParam String filter){
		try {
			List<User> users = userService.usersFilter(filter);
			return ResponseEntity.ok(users);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                .body(e.getMessage());
		}
		
	}
	
	@PostMapping(value = "users")
	public ResponseEntity<?> saveUser(@Valid @RequestBody User user){
		try {
			List<User> users = userService.save(user);
			return ResponseEntity.ok(users);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                .body(e.getMessage());
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body(e.getMessage());
		}
		
	}
	
	@PatchMapping("users/{id}")
	public ResponseEntity<?> updateUser(
	        @PathVariable String id,
	        @RequestBody Map<String, ?> updates) {

	    try {
	        User user = userService.update(id, updates);
	        return ResponseEntity.ok(user);
	    } catch (IllegalArgumentException e) {
	        return ResponseEntity.badRequest().body(e.getMessage());
	    }
	}
	
	@DeleteMapping("users/{id}")
	public ResponseEntity<?> deleteUser(
	        @PathVariable String id) {
	    try {
	        List<User> users = userService.remove(id);
	        return ResponseEntity.ok(users);
	    } catch (IllegalArgumentException e) {
	        return ResponseEntity.badRequest().body(e.getMessage());
	    }
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleValidation(MethodArgumentNotValidException ex){

	    Map<String, String> errors = new HashMap<>();

	    ex.getBindingResult().getFieldErrors().forEach(error -> {
	        errors.put(error.getField(), error.getDefaultMessage());
	    });

	    return ResponseEntity
	            .status(HttpStatus.BAD_REQUEST)
	            .body(errors);
	}
}
