package com.example.blob.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.blob.entity.Category;
import com.example.blob.exceptions.ResourceNotFoundException;
import com.example.blob.payloads.CategoryDto;
import com.example.blob.repositories.CategoryRepositry;
import com.example.blob.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepositry categoryRepo;
	
	@Autowired
	private ModelMapper modalMapper;
	
	
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category category = this.modalMapper.map(categoryDto, Category.class);
		
		Category save = this.categoryRepo.save(category);
		
		return this.modalMapper.map(save, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "Id", categoryId));
		
		category.setCategoryTitle(categoryDto.getCategoryTitle());
		category.setCategoryDescription(categoryDto.getCategoryDescription());
		
		Category save = this.categoryRepo.save(category);
		return this.modalMapper.map(save, CategoryDto.class);
	}

	@Override
	public void deleteCategory(Integer categoryId) {

		Category category = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "Id", categoryId));
		
		this.categoryRepo.delete(category);

	}

	@Override
	public CategoryDto getCategory(Integer categoryId) {
		Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "Id", categoryId));
		return this.modalMapper.map(category, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getAllCategory() {
		List<Category> listOfCategory = this.categoryRepo.findAll();
		
		List<CategoryDto> catDto = listOfCategory.stream().map((cat)->this.modalMapper.map(cat, CategoryDto.class)).collect(Collectors.toList());
		
		return catDto;
	}

}
