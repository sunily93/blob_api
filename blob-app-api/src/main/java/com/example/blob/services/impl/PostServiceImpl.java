package com.example.blob.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.blob.entity.Category;
import com.example.blob.entity.Post;
import com.example.blob.entity.User;
import com.example.blob.exceptions.ResourceNotFoundException;
import com.example.blob.payloads.PostDto;
import com.example.blob.payloads.PostResponse;
import com.example.blob.repositories.CategoryRepositry;
import com.example.blob.repositories.PostRepository;
import com.example.blob.repositories.UserRepo;
import com.example.blob.services.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepository postrepo;
	
	@Autowired
	private ModelMapper modalMapper;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CategoryRepositry catRepo;
	
	@Override
	public PostDto createPost(PostDto postDto,Integer userId,Integer categoryId) {
		
		User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "id", userId));
		
		Category category = this.catRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "Id", categoryId));
		
		Post post = this.modalMapper.map(postDto, Post.class);
		post.setPostImage("default.png");
		post.setPostDate(new Date());
		post.setUser(user);
		post.setCategory(category);
		
		Post savePost = this.postrepo.save(post);	
				
		return this.modalMapper.map(savePost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		
		Post post = this.postrepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "id", postId));
		
		post.setPostTitle(postDto.getPostTitle());
		post.setPostContent(postDto.getPostContent());
		post.setPostImage(postDto.getPostImage());
		
		Post savePost = this.postrepo.save(post);
		return this.modalMapper.map(savePost, PostDto.class);
	}

	@Override
	public void deletePost(Integer postId) {
		Post post = this.postrepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "id", postId));
		this.postrepo.delete(post);
	}

	@Override
	public PostResponse getAllPost(Integer pageNumber,Integer pageSize,String sortBy,String sortDir) {
			
		
		Sort sort=null;
		if(sortDir.equalsIgnoreCase("asc"))
		{
			sort=Sort.by(sortBy).ascending();
		}
		else {
			sort=Sort.by(sortBy).descending();
		}
		PageRequest pageRequest = PageRequest.of(pageNumber, pageSize,sort);
		
		//List<Post> findAll = this.postrepo.findAll();
		 Page<Post> pagePost = this.postrepo.findAll(pageRequest);
		 List<Post> findAll = pagePost.getContent();
		List<PostDto> postDto = findAll.stream().map((post)-> this.modalMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		PostResponse postResponse = new PostResponse();
		
		postResponse.setContent(postDto);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElements(pagePost.getNumberOfElements());
		postResponse.setTotalPages(pagePost.getTotalPages());
		postResponse.setLastPage(pagePost.isLast());
		
		
		return postResponse;
	}

	@Override
	public PostDto getPostById(Integer postId) {

		Post post = this.postrepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "Id", postId));
					
		return this.modalMapper.map(post, PostDto.class);
	}

	@Override
	public List<PostDto> getPostByCategory(Integer categoryId) {
		
		Category category = this.catRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "Id", categoryId));
		
		List<Post> listPost = this.postrepo.findByCategory(category);
		
		List<PostDto> postDto = listPost.stream().map((post)->this.modalMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		return postDto;
	}

	@Override
	public List<PostDto> getPostByUser(Integer userId) {
		User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "id", userId));
		
		List<Post> posts = this.postrepo.findByUser(user);
		
		List<PostDto> postDto = posts.stream().map((post)->this.modalMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		return postDto;
	}

	@Override
	public List<PostDto> searchPost(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

}
