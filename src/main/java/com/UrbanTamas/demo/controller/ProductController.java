package com.UrbanTamas.demo.controller;

import com.UrbanTamas.demo.data.dto.response.PaginatedResponse;
import com.UrbanTamas.demo.data.entity.ProductEntity;
import com.UrbanTamas.demo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public PaginatedResponse<ProductEntity> getProducts(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Boolean favorite,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false) List<String> types,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "4") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ProductEntity> productPage = productService.getProducts(name, favorite, minPrice, maxPrice, types, pageable);
        return new PaginatedResponse<>(productPage.getContent(), productPage.getTotalElements());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductEntity> getProduct(@PathVariable Integer id) {
        return productService.getProduct(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}/favorite")
    public ResponseEntity<ProductEntity> toggleFavorite(@PathVariable Integer id) {
        return productService.toggleFavorite(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/related")
    public ResponseEntity<List<ProductEntity>> getRelatedProducts(@PathVariable Integer id) {
        List<ProductEntity> related = productService.getRelatedProducts(id);
        return ResponseEntity.ok(related);
    }
}
