package com.example.blob.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.blob.entity.User;
import com.example.blob.exceptions.ResourceNotFoundException;
import com.example.blob.payloads.UserDto;
import com.example.blob.repositories.UserRepo;
import com.example.blob.services.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepo userRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public UserDto createUser(UserDto userDto) {
		
		
		 User user = this.dtoToUser(userDto);
		
		User saveUser = this.userRepository.save(user);
		
		return this.userToDto(saveUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		
		User user = this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
		
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		
		User saveUser = this.userRepository.save(user);
		
		UserDto userToDto = this.userToDto(saveUser);
		
		return userToDto;
	}

	@Override
	public UserDto getUserById(Integer userId) {
		User user = this.userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "Id", userId));
				
		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {

		List<User> findAll = this.userRepository.findAll();
		
		List<UserDto> listOfUserDto = findAll.stream().map(user->this.userToDto(user)).collect(Collectors.toList());
		
		
		return listOfUserDto;
	}

	@Override
	public void deleteUser(Integer userId) {
		User user = this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "Id", userId));
		
		this.userRepository.delete(user);
	}
	
	public User dtoToUser(UserDto userDto)
	{
		User user = this.modelMapper.map(userDto, User.class);
		/*User user = new User();
		user.setId(userDto.getId());
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setAbout(userDto.getAbout());
		user.setPassword(userDto.getPassword());*/
		
		return user;
	}
	
	public UserDto userToDto(User user)
	{
		UserDto userDto = this.modelMapper.map(user, UserDto.class);
/*		UserDto userDto = new UserDto();
		userDto.setId(user.getId());
		userDto.setName(user.getName());
		userDto.setEmail(user.getEmail());
		userDto.setAbout(user.getAbout());
		userDto.setPassword(user.getPassword());*/
		
		return userDto;
	}

}
