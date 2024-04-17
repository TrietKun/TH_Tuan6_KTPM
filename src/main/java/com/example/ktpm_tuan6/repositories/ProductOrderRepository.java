package com.example.ktpm_tuan6.repositories;

import com.example.ktpm_tuan6.entities.Product_order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductOrderRepository extends JpaRepository<Product_order, Long> {
}