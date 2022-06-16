package com.example.blob.controller;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder.In;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.blob.payloads.ApiResponse;
import com.example.blob.payloads.UserDto;
import com.example.blob.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	//Post -create user
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto dto)
	{
		UserDto userDto = this.userService.createUser(dto);
		
		return new ResponseEntity<UserDto>(userDto,HttpStatus.CREATED);
	}
	
	//Put -update user
	
	@PutMapping("/{userId}")	
	public ResponseEntity<UserDto> updateUser(@Valid@RequestBody UserDto dto,@PathVariable Integer userId)
	{
		UserDto updateUser = this.userService.updateUser(dto, userId);
		
		return ResponseEntity.ok(updateUser);
	}
	
	//Delete - delete user
	
	@DeleteMapping("/{userId}")
	public ResponseEntity<?> deleteUser(@PathVariable("userId") Integer uId)
	{
		 this.userService.deleteUser(uId);
		 return new ResponseEntity<ApiResponse>(new ApiResponse("User Deleted Succesfully......",true),HttpStatus.OK);//ResponseEntity.ok(Map.of("message","User Deleted Succesfully"));
	}
	
	//Get - get user
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUser()
	{
		return ResponseEntity.ok(this.userService.getAllUsers());
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getSingleUser(@PathVariable Integer userId)
	{
		return ResponseEntity.ok(this.userService.getUserById(userId));
	}
		
}
