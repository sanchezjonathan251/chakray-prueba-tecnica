package com.prueba.tecnica.chakray.service;

import java.util.List;

import com.prueba.tecnica.chakray.model.User;

public interface UserService {

	List<User> allUsersSortedH2(String sortedBy) throws IllegalArgumentException;
	List<User> allUsersSortedArray(String sortedBy) throws IllegalArgumentException;
}
