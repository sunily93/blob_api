package com.example.blob.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.blob.entity.Category;
import com.example.blob.entity.Post;
import com.example.blob.entity.User;

public interface PostRepository extends JpaRepository<Post, Integer>{

	List<Post> findByUser(User user);
	List<Post> findByCategory(Category category);
	
}
