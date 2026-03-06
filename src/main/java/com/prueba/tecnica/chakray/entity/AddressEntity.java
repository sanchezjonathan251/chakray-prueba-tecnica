package com.prueba.tecnica.chakray.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "address")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressEntity {
	@Id
	private Long id;
	@Column
    private String name;
	@Column
    private String street;
	@Column
    private String countryCode;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
