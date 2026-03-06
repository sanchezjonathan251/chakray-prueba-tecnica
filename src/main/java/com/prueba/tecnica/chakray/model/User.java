package com.prueba.tecnica.chakray.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
	private UUID id;
    private String email;
    private String name;
    private String phone;
    private String password;
    private String taxId;
    private LocalDateTime createdAt;
    private List<Address> addresses;
    
}                                         
                                           