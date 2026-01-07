package com.UrbanTamas.demo.repository;

import com.UrbanTamas.demo.data.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer>, JpaSpecificationExecutor<ProductEntity> {
    Page<ProductEntity> findDistinctByTypesInAndIdNot(List<String> types, Integer id, Pageable pageable);
}
