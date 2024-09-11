package com.example.auth.controllers.admincontoller;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.auth.dto.ProductDto;
import com.example.auth.entity.Product;
import com.example.auth.services.adminproduct.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class AdminProductController {
	
	private final ProductService productService;

	
	public AdminProductController(ProductService productService) {
		
		this.productService = productService;
	}
	
	@PostMapping("/product")
	public ResponseEntity<ProductDto> addProduct(@ModelAttribute ProductDto productDto)throws IOException {
		ProductDto productDto1 = productService.addProduct(productDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(productDto1);
	}
	
	@GetMapping("/products")
	public ResponseEntity<List<ProductDto>> getAllProducts(){
		List<ProductDto> productDtos = productService.getAllProducts();
		return ResponseEntity.ok(productDtos);
	}

}
