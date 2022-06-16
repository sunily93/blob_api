package com.example.blob.controller;

import java.util.List;

import org.hibernate.annotations.UpdateTimestamp;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.blob.payloads.ApiResponse;
import com.example.blob.payloads.PostDto;
import com.example.blob.payloads.PostResponse;
import com.example.blob.services.PostService;

@RestController
@RequestMapping("/api/")
public class PostController {

	@Autowired
	private PostService postService;
	
	//create
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto,@PathVariable Integer userId,@PathVariable Integer categoryId)
	{
		PostDto dto = this.postService.createPost(postDto, userId, categoryId);
		return new ResponseEntity<PostDto>(dto,HttpStatus.CREATED);
	}
	
	//get post by user
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer userId)
	{
		List<PostDto> postByUser = this.postService.getPostByUser(userId);
		
		return new ResponseEntity<List<PostDto>>(postByUser,HttpStatus.OK);
				
	}
	
	//get post by category
		@GetMapping("/category/{categoryId}/posts")
		public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer categoryId)
		{
			List<PostDto> postByUser = this.postService.getPostByCategory(categoryId);
			
			return new ResponseEntity<List<PostDto>>(postByUser,HttpStatus.OK);
					
		}
		
	//get all post
		@GetMapping("/posts")
		public ResponseEntity<PostResponse> getAllPost(
				@RequestParam(value="pageNumber",defaultValue="0",required=false)Integer pageNumber,
				@RequestParam(value="pageSize",defaultValue="10",required=false)Integer pageSize,
				@RequestParam(value="sortBy",defaultValue="postId",required=false)String sortBy,
				@RequestParam(value="sortDir",defaultValue="asc",required=false)String sortDir
				)
		{
			//List<PostDto> allPost = this.postService.getAllPost(pageNumber,pageSize);
			 PostResponse allPost = this.postService.getAllPost(pageNumber,pageSize,sortBy,sortDir);
			return new ResponseEntity<PostResponse>(allPost,HttpStatus.OK);
		}
		
	//get single post	
		
		@GetMapping("/posts/{postId}")
		public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId)
		{
			PostDto postById = this.postService.getPostById(postId);
			return new ResponseEntity<PostDto>(postById,HttpStatus.OK);
		}
		
	//delete post
		@DeleteMapping("/posts/{postId}")
		public ApiResponse deletePost(@PathVariable Integer postId)
		{
			this.postService.deletePost(postId);
			return new ApiResponse("Post Successfully deleted....",true);
		}
	
	//update post	
		
		@PutMapping("/posts/{postId}")
		public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable Integer postId)
		{
			PostDto updatePost = this.postService.updatePost(postDto, postId);
			return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK);
		}
}