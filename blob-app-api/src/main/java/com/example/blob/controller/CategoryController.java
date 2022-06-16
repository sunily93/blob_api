package com.example.blob.controller;

import java.util.List;

import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.RestController;

import com.example.blob.payloads.ApiResponse;
import com.example.blob.payloads.CategoryDto;
import com.example.blob.services.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	//create
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@Valid@RequestBody CategoryDto categoryDto)
	{
		CategoryDto category = this.categoryService.createCategory(categoryDto);
		
		return new ResponseEntity<CategoryDto>(category,HttpStatus.CREATED);
	}
	
	//update
	@PutMapping("/{catId}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid@RequestBody CategoryDto categoryDto,@PathVariable Integer catId)
	{
		CategoryDto updateCategory = this.categoryService.updateCategory(categoryDto,catId);
		
		return new ResponseEntity<CategoryDto>(updateCategory,HttpStatus.OK);
	}	
	
	//delete
	@DeleteMapping("/{catId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer catId)
	{
		this.categoryService.deleteCategory(catId);
		
		return new ResponseEntity<ApiResponse>(new ApiResponse("Category deleted Successfully ",true),HttpStatus.OK);
	}	
	//get single category
	@GetMapping("/{catId}")
	public ResponseEntity<CategoryDto> getCategory(@PathVariable Integer catId)
	{
		CategoryDto categoryDto = this.categoryService.getCategory(catId);
		
		return new ResponseEntity<CategoryDto>(categoryDto,HttpStatus.OK);
	}
	
	//get all category
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getAllCategory()
	{
		List<CategoryDto> allCategory = this.categoryService.getAllCategory();
		
		return ResponseEntity.ok(allCategory);
		
	}
	
}
