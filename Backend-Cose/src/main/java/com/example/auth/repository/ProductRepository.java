package com.example.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.auth.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

}
