package com.UrbanTamas.demo.service;

import com.UrbanTamas.demo.data.entity.ProductEntity;
import com.UrbanTamas.demo.repository.ProductRepository;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Page<ProductEntity> getProducts(String name, Boolean favorite, Double minPrice, Double maxPrice, List<String> types, Pageable pageable) {
        Specification<ProductEntity> spec = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (name != null && !name.isEmpty()) {
                predicates.add(cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%"));
            }

            if (favorite != null) {
                predicates.add(cb.equal(root.get("favourite"), favorite));
            }

            if (minPrice != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("price"), minPrice));
            }

            if (maxPrice != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("price"), maxPrice));
            }

            if (types != null && !types.isEmpty()) {
                Join<ProductEntity, String> typesJoin = root.join("types");
                predicates.add(typesJoin.in(types));
                query.distinct(true);
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };

        return productRepository.findAll(spec, pageable);
    }

    @Override
    public Optional<ProductEntity> getProduct(Integer id) {
        return productRepository.findById(id);
    }

    @Override
    public Optional<ProductEntity> toggleFavorite(Integer id) {
        return productRepository.findById(id).map(product -> {
            product.setFavourite(!product.getFavourite());
            return productRepository.save(product);
        });
    }
}
