package com.example.auth.services.admin.category;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.auth.dto.CategoryDto;
import com.example.auth.entity.Category;

@Service
public interface CategoryService {
	
	Category createCategory(CategoryDto categoryDto);
	
	List<Category> getAllCategories();

}
