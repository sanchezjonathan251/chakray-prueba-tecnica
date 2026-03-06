package com.prueba.tecnica.chakray.utils;

import java.util.List;

import com.prueba.tecnica.chakray.entity.UserEntity;
import com.prueba.tecnica.chakray.model.Address;
import com.prueba.tecnica.chakray.model.User;

public class UserMapper {

	public static User toDTO(UserEntity user) {

        List<Address> addresses = user.getAddresses()
                .stream()
                .map(AddressMapper::toDTO)
                .toList();

        return User.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .phone(user.getPhone())
                .taxId(user.getTaxId())
                .createdAt(user.getCreatedAt())
                .addresses(addresses)
                .build();
	}
}
