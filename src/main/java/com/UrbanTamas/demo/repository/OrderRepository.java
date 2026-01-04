package com.UrbanTamas.demo.repository;

import com.UrbanTamas.demo.data.entity.OrderEntity;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {
}
