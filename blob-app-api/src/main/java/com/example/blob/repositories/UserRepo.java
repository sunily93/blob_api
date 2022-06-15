package com.example.blob.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.blob.entity.User;

public interface UserRepo extends JpaRepository<User, Integer>{

}
