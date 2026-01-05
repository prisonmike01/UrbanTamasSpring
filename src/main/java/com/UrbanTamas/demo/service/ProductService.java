package com.UrbanTamas.demo.service;

import com.UrbanTamas.demo.data.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Page<ProductEntity> getProducts(String name, Boolean favorite, Double minPrice, Double maxPrice, List<String> types, Pageable pageable);
    Optional<ProductEntity> getProduct(Integer id);
    Optional<ProductEntity> toggleFavorite(Integer id);
}
