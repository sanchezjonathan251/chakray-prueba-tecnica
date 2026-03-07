package com.prueba.tecnica.chakray.repository;

import java.util.List;

import com.prueba.tecnica.chakray.model.User;

public interface UserRepository {

	List<User> allUsers();
	List<User> saveUser(User user);
}
