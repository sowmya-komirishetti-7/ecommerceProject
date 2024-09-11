package com.example.auth.services.adminproduct;

import java.io.IOException;
import java.util.List;
import java.util.Locale.Category;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.auth.dto.ProductDto;
import com.example.auth.entity.Product;
import com.example.auth.repository.CategoryRepository;
import com.example.auth.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{
	
	private final ProductRepository productRepository;
	
	private final CategoryRepository categoryRepository;

	
	public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
		
		this.productRepository = productRepository;
		this.categoryRepository = categoryRepository;
	}
	
	public ProductDto addProduct(ProductDto productDto) throws IOException {
		Product product = new Product();
		product.setName(productDto.getName());
		product.setDescription(productDto.getDescription());
		product.setPrice(productDto.getPrice());
		product.setImg(productDto.getImg().getBytes());
		
		com.example.auth.entity.Category category = categoryRepository.findById(productDto.getCategoryId()).orElseThrow();
		
		product.setCategory(category);
		return productRepository.save(product).getDto();
		}
	
	public List<ProductDto> getAllProducts(){
		List<Product> products = productRepository.findAll();
		return products.stream().map(Product::getDto).collect(Collectors.toList());
	}
	

}
