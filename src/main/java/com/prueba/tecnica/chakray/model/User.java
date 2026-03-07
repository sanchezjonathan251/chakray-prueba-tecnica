package com.prueba.tecnica.chakray.model;


import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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
    @Pattern(
    	    regexp = "^(\\+?\\d{1,3})?\\d{10}$",
    	    message = "phone must be 10 digits and may include country code"
    	)
    private String phone;
    @NotBlank
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @NotBlank
    @Pattern(
            regexp = "^[A-Z]{4}[0-9]{6}[A-Z0-9]{3}$",
            message = "tax_id must be valid RFC format"
        )
    private String taxId;
    private String createdAt;
    private List<Address> addresses;
    
}                                         
                                           