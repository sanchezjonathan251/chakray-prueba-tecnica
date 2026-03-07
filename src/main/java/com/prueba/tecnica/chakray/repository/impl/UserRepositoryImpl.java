package com.prueba.tecnica.chakray.repository.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.prueba.tecnica.chakray.model.Address;
import com.prueba.tecnica.chakray.model.User;
import com.prueba.tecnica.chakray.repository.UserRepository;

import jakarta.annotation.PostConstruct;

@Repository
public class UserRepositoryImpl implements UserRepository{
	
	private List<User> users = new ArrayList<User>();
	
	@PostConstruct
	private void init() {
		users.add(
		        new User(
		            UUID.fromString("11111111-1111-1111-1111-111111111111"),
		            "user1@mail.com",
		            "user1",
		            "5551234567",
		            "encryptedpass1",
		            "AARR990101XXX",
		            "2026-01-01T10:00:00",
		            List.of(
		                new Address(1L,"workaddress","street No. 1","UK"),
		                new Address(2L,"homeaddress","street No. 2","AU")
		            )
		        )
		    );

		    users.add(
		        new User(
		            UUID.fromString("22222222-2222-2222-2222-222222222222"),
		            "dev@mail.com",
		            "developerUser",
		            "5559876543",
		            "encryptedpass2",
		            "BBBB990101YYY",
		            "2026-01-01T11:00:00",
		            List.of(
		                new Address(3L,"office","street No. 3","US")
		            )
		        )
		    );

		    users.add(
		        new User(
		            UUID.fromString("33333333-3333-3333-3333-333333333333"),
		            "admin@test.com",
		            "admin",
		            "5215512345678",
		            "encryptedpass3",
		            "CCCC990101ZZZ",
		            "2026-01-01T12:00:00",
		            List.of(
		                new Address(4L,"main","street No. 4","CA"),
		                new Address(5L,"vacation","street No. 5","ES")
		            )
		        )
		    );
	}

	@Override
	public List<User> allUsers() {
		return users;
	}

	@Override
	public List<User> saveUser(User user) {
		users.add(user);
		return users;
	}
}
