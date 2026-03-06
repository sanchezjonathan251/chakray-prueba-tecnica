package com.prueba.tecnica.chakray.utils;

import java.util.Comparator;

import com.prueba.tecnica.chakray.model.User;

public enum UserSortField {
	NAME("name", Comparator.comparing(User::getName)),
    EMAIL("email", Comparator.comparing(User::getEmail)),
    CREATED_AT("createdAt", Comparator.comparing(User::getCreatedAt)),
    PHONE("phone", Comparator.comparing(User::getPhone)),
    TAX_ID("taxId", Comparator.comparing(User::getTaxId));

	
	private final String field;
    private final Comparator<User> comparator;

    UserSortField(String field, Comparator<User> comparator) {
        this.field = field;
        this.comparator = comparator;
    }

    public String getField() {
        return field;
    }
    
    public Comparator<User> getComparator() {
        return comparator;
    }

    public static UserSortField from(String value) throws IllegalArgumentException{
        for (UserSortField f : values()) {
            if (f.field.equalsIgnoreCase(value)) {
                return f;
            }
        }
        throw new IllegalArgumentException("Invalid sort field: " + value);
    }
}
