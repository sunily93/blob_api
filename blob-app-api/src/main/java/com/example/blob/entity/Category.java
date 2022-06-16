package com.example.blob.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Category {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer categoryId;
	
	private String categoryTitle;
	
	@Column(name="description")
	private String categoryDescription;
	
	@OneToMany(mappedBy="category", cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	private List<Post> posts= new ArrayList<Post>(); 
}
