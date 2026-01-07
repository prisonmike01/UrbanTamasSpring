package com.UrbanTamas.demo.controller;

import com.UrbanTamas.demo.data.dto.CartItem;
import com.UrbanTamas.demo.data.dto.request.CartRequest;
import com.UrbanTamas.demo.data.entity.ProductEntity;
import com.UrbanTamas.demo.service.ProductService;
import com.UrbanTamas.demo.session.CartSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class CartController {
    private final CartSession cartSession;
    private final ProductService productService;

    @GetMapping
    public List<CartItem> getCart() {
        return cartSession.getItems();
    }

    @PostMapping
    public ResponseEntity<List<CartItem>> addToCart(@RequestBody CartRequest request) {
        Optional<CartItem> existingItem = cartSession.getItems().stream()
                .filter(item -> item.getProduct().getId().equals(request.getProductId()))
                .findFirst();

        if(existingItem.isPresent()) {
            existingItem.get().setQuantity((existingItem.get().getQuantity() + request.getQuantity()));
        } else {
            Optional<ProductEntity> product = productService.getProduct(request.getProductId());

            if(product.isPresent()) {
                cartSession.addItem(new CartItem(product.get(), request.getQuantity()));
            } else {
                return ResponseEntity.notFound().build();
            }
        }

        return ResponseEntity.ok(cartSession.getItems());
    }

    @PatchMapping("/{productId}")
    public ResponseEntity<List<CartItem>> updateQuantity(@PathVariable Integer productId, @RequestBody CartRequest request) {
        Optional<CartItem> existingItem = cartSession.getItems().stream()
                .filter(item -> item.getProduct().getId().equals(request.getProductId()))
                .findFirst();

        if(existingItem.isPresent()) {
            existingItem.get().setQuantity((request.getQuantity()));
            return ResponseEntity.ok(cartSession.getItems());
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<List<CartItem>> removeFromCart(@PathVariable Integer productId) {
        cartSession.getItems().removeIf(item -> item.getProduct().getId().equals(productId));
        return ResponseEntity.ok(cartSession.getItems());
    }

    @DeleteMapping
    public ResponseEntity<List<CartItem>> clearCart() {
        cartSession.clear();
        return ResponseEntity.ok(cartSession.getItems());
    }
}
