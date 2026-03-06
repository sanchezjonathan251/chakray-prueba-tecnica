package com.prueba.tecnica.chakray.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.prueba.tecnica.chakray.model.User;
import com.prueba.tecnica.chakray.repository.UserRepository;
import com.prueba.tecnica.chakray.repository.UserRepositoryH2;
import com.prueba.tecnica.chakray.service.UserService;
import com.prueba.tecnica.chakray.utils.UserMapper;
import com.prueba.tecnica.chakray.utils.UserSortField;


@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepositoryH2 userRepositoryH2;

	@Autowired
	private UserRepository userRepository;
	
	
	@Override
	public List<User> allUsersSortedH2(String sortBy) throws IllegalArgumentException{
		Sort sort = Sort.unsorted();

	    if (sortBy != null && !sortBy.isBlank()) {
	        UserSortField sortField = UserSortField.from(sortBy);
	        sort = Sort.by(sortField.getField());
	    }
		
		return userRepositoryH2.findAll(sort)
				.stream()
				.map(UserMapper::toDTO)
				.toList();
	}

	@Override
	public List<User> allUsersSortedArray(String sortBy) throws IllegalArgumentException{
		List<User> users = userRepository.allUsers();
		
		if (sortBy != null && !sortBy.isBlank()) {
			UserSortField sortField = UserSortField.from(sortBy);
			return users.stream().sorted(sortField.getComparator()).toList();
	    }
		return users;
	}

	@Override
	public List<User> usersFilter(String filter) throws IllegalArgumentException {
		List<User> users = userRepository.allUsers();
		
		if(filter == null || filter.isBlank()) {
	        throw new IllegalArgumentException("Filter cannot be empty");
	    }

	    String[] parts = filter.split("\\:");
	    if(parts.length != 3) {
	        throw new IllegalArgumentException("Filter must be in format attribute+operator+value");
	    }
	    
	    String attribute = parts[0];
	    String operator = parts[1];
	    String value = parts[2];
	    
		return users.stream()
	            .filter(user -> matches(user, attribute, operator, value))
	            .toList();
	}

	@Override
	public List<User> save(User user) throws IllegalArgumentException {
		
		return null;
	}

	@Override
	public List<User> update(String id) throws IllegalArgumentException {
		
		return null;
	}

	@Override
	public List<User> remove(String id) throws IllegalArgumentException {
		
		return null;
	}
	
	
	private boolean matches(User user, String attribute, String operator, String value) throws IllegalArgumentException {
	    String fieldValue;

	    switch(attribute) {
	        case "name": fieldValue = user.getName(); break;
	        case "email": fieldValue = user.getEmail(); break;
	        case "phone": fieldValue = user.getPhone(); break;
	        case "tax_id": fieldValue = user.getTaxId(); break;
	        case "id": fieldValue = user.getId().toString(); break;
	        case "created_at": fieldValue = user.getCreatedAt().toString(); break;
	        default: throw new IllegalArgumentException("Invalid attribute: " + attribute);
	    }

	    if(fieldValue == null) return false;

	    return switch(operator) {
	        case "co" -> fieldValue.contains(value);
	        case "eq" -> fieldValue.equals(value);
	        case "sw" -> fieldValue.startsWith(value);
	        case "ew" -> fieldValue.endsWith(value);
	        default -> throw new IllegalArgumentException("Invalid operator: " + operator);
	    };
	}

}
