package com.spring.delivery.domain.request;

import com.spring.delivery.util.anotation.PasswordValid;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RequestRegister {
	@NotBlank(message = "Id token is required")
	String idToken;

	@NotBlank(message = "Region is required")
	String region;

	@NotBlank(message = "Phone number is required")
	String phoneNumber;

	@NotBlank(message = "Email is required")
	@Email(message = "Please provide a valid phoneNumber address")
	String email;

	@NotBlank(message = "Full name is required")
	String fullName;


	@NotBlank(message = "Password is required")
	@PasswordValid(message = "Password is need at least 8 characters; 1 uppercase; 1 lowercase; 1 number and 1 special character")
	String password;
}
