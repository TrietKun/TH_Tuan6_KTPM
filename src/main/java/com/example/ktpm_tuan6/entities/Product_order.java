package com.example.ktpm_tuan6.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Table(name = "product_order")
@ToString
@Entity
public class Product_order {
    @Id
    private String id;
    private String name;
    private String description;
    private double price;
    private int quantity;
    private String category;
    private String customerName;

    public String getCustomerName() {
        return customerName;
    }
}
