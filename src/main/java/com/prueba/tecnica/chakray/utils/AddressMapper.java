package com.prueba.tecnica.chakray.utils;

import com.prueba.tecnica.chakray.entity.AddressEntity;
import com.prueba.tecnica.chakray.model.Address;

public class AddressMapper {

	public static Address toDTO(AddressEntity address) {

        return Address.builder()
                .id(address.getId())
                .name(address.getName())
                .street(address.getStreet())
                .countryCode(address.getCountryCode())
                .build();
    }
}
