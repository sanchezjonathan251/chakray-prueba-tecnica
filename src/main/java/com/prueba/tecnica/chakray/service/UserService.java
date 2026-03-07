package com.prueba.tecnica.chakray.service;

import java.util.List;
import java.util.Map;

import com.prueba.tecnica.chakray.model.User;

public interface UserService {

	List<User> allUsersSortedH2(String sortedBy);
	List<User> allUsersSortedArray(String sortedBy);
	List<User> usersFilter(String filter);
	List<User> save(User user);
	User update(String id, Map<String, ?> updt);
	List<User> remove(String id);
}
