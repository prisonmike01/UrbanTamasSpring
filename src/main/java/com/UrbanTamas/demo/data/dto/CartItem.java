package com.UrbanTamas.demo.data.dto;

import com.UrbanTamas.demo.data.entity.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {
    private ProductEntity product;
    private int quantity;
}
