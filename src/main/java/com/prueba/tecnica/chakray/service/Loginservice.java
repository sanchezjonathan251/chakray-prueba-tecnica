package com.prueba.tecnica.chakray.service;

import com.prueba.tecnica.chakray.model.LoginRequest;
import com.prueba.tecnica.chakray.model.User;

public interface Loginservice {
	User login(LoginRequest loginRequest);
}
