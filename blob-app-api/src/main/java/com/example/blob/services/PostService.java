package com.example.blob.services;

import java.util.List;

import com.example.blob.entity.Post;
import com.example.blob.payloads.PostDto;
import com.example.blob.payloads.PostResponse;

public interface PostService {

	//create
	
	PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);
	
	//update
	PostDto updatePost(PostDto postDto,Integer postId);
	
	//delete
	void deletePost(Integer postId);
	
	//get all Post
	//List<PostDto> getAllPost(Integer pageNumber,Integer pageSize);
	PostResponse getAllPost(Integer pageNumber,Integer pageSize,String sortBy,String sortDir);
	
	//get single post
	PostDto getPostById(Integer postId);
	
	//get all post by category
	List<PostDto> getPostByCategory(Integer categoryId);
	
	//get all post by user
	List<PostDto> getPostByUser(Integer userId);
	
	//search post by id
	List<PostDto> searchPost(String keyword);
	
	
}
