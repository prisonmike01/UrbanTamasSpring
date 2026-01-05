package com.UrbanTamas.demo.service;

import com.UrbanTamas.demo.data.entity.ProductEntity;
import com.UrbanTamas.demo.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public List<ProductEntity> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Optional<ProductEntity> getProduct(Integer id) {
        return productRepository.findById(id);
    }
}
