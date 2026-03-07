package com.prueba.tecnica.chakray.entity;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
	@Id
	private UUID id;
	@Column
    private String email;
	@Column
    private String name;
	@Column
    private String phone;
	@Column
    private String password;
	@Column
    private String taxId;
	@Column
    private String createdAt;
	@OneToMany(mappedBy = "user")
    private List<AddressEntity> addresses;
    
}                                         
                                           