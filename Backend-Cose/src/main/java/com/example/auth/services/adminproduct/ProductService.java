package com.example.auth.services.adminproduct;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.auth.dto.ProductDto;


public interface ProductService {
	
	ProductDto addProduct(ProductDto productDto) throws IOException;
	
	List<ProductDto> getAllProducts();

}
