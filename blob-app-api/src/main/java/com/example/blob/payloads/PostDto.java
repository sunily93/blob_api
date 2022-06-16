package com.example.blob.payloads;

import java.util.Date;

import com.example.blob.entity.Category;
import com.example.blob.entity.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {

	private Integer postId;
	
	private String postTitle;
	
	private String postContent;

	private String postImage;
	
	private Date postDate;
	
	private CategoryDto category;
	
	private UserDto user;
}
