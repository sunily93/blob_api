package com.example.blob.payloads;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

	private int id;
	
	@NotEmpty
	@Size(min=4 ,message="User Name must be 4 characters")
	private String name;
	
	@Email(message="Email address is not valid!!")
	private String email;
	
	@NotEmpty
	private String password;
	
	@NotNull
	private String about;
}
