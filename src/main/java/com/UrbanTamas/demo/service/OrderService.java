package com.UrbanTamas.demo.service;

import com.UrbanTamas.demo.data.entity.OrderEntity;

import java.util.Optional;

public interface OrderService {
    Optional<OrderEntity> getOrder(Integer id);
}
