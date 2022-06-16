package com.example.blob.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="posts")
public class Post {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer postId;
		
	private String postTitle;
	
	private String postContent;

	private String postImage;
	
	private Date postDate;
	
	@ManyToOne
	@JoinColumn(name="categoryId")
	private Category category;
	
	@ManyToOne
	private User user;
	
}
