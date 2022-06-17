package com.example.blob.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.blob.entity.Comment;
import com.example.blob.entity.Post;
import com.example.blob.exceptions.ResourceNotFoundException;
import com.example.blob.payloads.CommentDto;
import com.example.blob.repositories.CommentRepo;
import com.example.blob.repositories.PostRepository;
import com.example.blob.services.CommentService;
@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private PostRepository postRepo;
	
	@Autowired
	private CommentRepo commentRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) {
		
		Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "Id", postId));
		
		Comment comment = this.modelMapper.map(commentDto, Comment.class);
		
		comment.setPost(post);
		
		Comment saveComment = this.commentRepo.save(comment);
		
		return this.modelMapper.map(saveComment, CommentDto.class);
	}

	@Override
	public void deleteComment(Integer commentId) {

		Comment comment = this.commentRepo.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment", "Id", commentId));
		
		this.commentRepo.delete(comment);
	}

}
