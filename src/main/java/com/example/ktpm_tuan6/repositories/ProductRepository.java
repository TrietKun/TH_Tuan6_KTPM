package com.example.ktpm_tuan6.repositories;

import com.example.ktpm_tuan6.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}