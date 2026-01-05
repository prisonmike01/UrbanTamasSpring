package com.UrbanTamas.demo.service;

import com.UrbanTamas.demo.data.entity.ProductEntity;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<ProductEntity> getAllProducts();
    Optional<ProductEntity> getProduct(Integer id);
}
