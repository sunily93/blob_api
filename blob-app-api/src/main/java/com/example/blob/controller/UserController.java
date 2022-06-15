package com.example.blob.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.blob.payloads.UserDto;
import com.example.blob.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	//Post -create user
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@RequestBody UserDto dto)
	{
		UserDto userDto = this.userService.createUser(dto);
		
		return new ResponseEntity<UserDto>(userDto,HttpStatus.CREATED);
	}
	
	//Put -update user
	
	
	
	//Delete - delete user
	
	//Get - get user
}
