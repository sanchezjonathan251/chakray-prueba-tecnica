package com.prueba.tecnica.chakray.service.impl;


import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.prueba.tecnica.chakray.model.User;
import com.prueba.tecnica.chakray.repository.UserRepository;
import com.prueba.tecnica.chakray.repository.UserRepositoryH2;
import com.prueba.tecnica.chakray.service.UserService;
import com.prueba.tecnica.chakray.utils.AESUtil;
import com.prueba.tecnica.chakray.utils.TimeUtil;
import com.prueba.tecnica.chakray.utils.UserMapper;
import com.prueba.tecnica.chakray.utils.UserSortField;


@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepositoryH2 userRepositoryH2;

	@Autowired
	private UserRepository userRepository;
	
	
	@Override
	public List<User> allUsersSortedH2(String sortBy){
		Sort sort = Sort.unsorted();

	    if (!Objects.equals(sortBy, null) && !sortBy.isBlank()) {
	        UserSortField sortField = UserSortField.from(sortBy);
	        sort = Sort.by(sortField.getField());
	    }
		
		return userRepositoryH2.findAll(sort)
				.stream()
				.map(UserMapper::toDTO)
				.toList();
	}

	@Override
	public List<User> allUsersSortedArray(String sortBy){
		List<User> users = userRepository.allUsers();
		
		if (!Objects.equals(sortBy, null) && !sortBy.isBlank()) {
			UserSortField sortField = UserSortField.from(sortBy);
			return users.stream().sorted(sortField.getComparator()).toList();
	    }
		return users;
	}

	@Override
	public List<User> usersFilter(String filter) {
		List<User> users = userRepository.allUsers();
		
		if(Objects.equals(filter, null) || filter.isBlank()) {
	        throw new IllegalArgumentException("Filter cannot be empty");
	    }

	    String[] parts = filter.split("\\:");
	    if(parts.length != 3) {
	        throw new IllegalArgumentException("Filter must be in format attribute:operator:value");
	    }
	    
	    String attribute = parts[0];
	    String operator = parts[1];
	    String value = parts[2];
	    
		return users.stream()
	            .filter(user -> matches(user, attribute, operator, value))
	            .toList();
	}

	@Override
	public List<User> save(User user) {
		
		boolean exists = userRepository.allUsers().stream()
		        .anyMatch(u -> u.getTaxId().equals(user.getTaxId()));

		if (exists) {
		    throw new IllegalArgumentException("tax_id already exists");
		}
		user.setId(UUID.randomUUID());
		try {
	        user.setPassword(AESUtil.encrypt(user.getPassword()));
	    } catch (Exception e) {
	    	e.printStackTrace();
	        throw new RuntimeException("Error encrypting password", e);
	    }
		user.setCreatedAt(TimeUtil.getMadagascarTimestamp());
		
		return userRepository.saveUser(user);
	}

	@Override
	public User update(String id, Map<String, ?> updt){

	    User user = userRepository.allUsers().stream()
	            .filter(u -> u.getId().toString().equals(id))
	            .findFirst()
	            .orElseThrow(() -> new IllegalArgumentException("User not found"));

	    updt.forEach((key, value) -> {

	        switch (key) {

	            case "email" -> user.setEmail((String) value);

	            case "name" -> user.setName((String) value);

	            case "phone" -> {

	                String phone = (String) value;

	                if (phone == null || phone.isBlank()) {
	                    throw new IllegalArgumentException("phone cannot be empty");
	                }

	                if (!phone.matches("^(\\+?\\d{1,3})?\\d{10}$")) {
	                    throw new IllegalArgumentException(
	                        "phone must be 10 digits and may include country code"
	                    );
	                }

	                user.setPhone(phone);
	            }

	            case "password" -> {

	                String password = (String) value;

	                if (password == null || password.isBlank()) {
	                    throw new IllegalArgumentException("password cannot be empty");
	                }

	                try {
	                    user.setPassword(AESUtil.encrypt(password));
	                } catch (Exception e) {
	                    throw new RuntimeException("Error encrypting password");
	                }
	            }

	            default -> throw new IllegalArgumentException("Invalid field: " + key);
	        }

	    });

	    return user;
	}

	@Override
	public List<User> remove(String id){
		List<User> users = userRepository.allUsers();

	    User user = users.stream()
	            .filter(u -> u.getId().toString().equals(id))
	            .findFirst()
	            .orElseThrow(() -> new IllegalArgumentException("User not found"));

	    users.remove(user);

	    return users;
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
