package com.UrbanTamas.demo.service;

import com.UrbanTamas.demo.data.entity.OrderEntity;
import com.UrbanTamas.demo.repository.OrderRepository;

import java.util.Optional;

public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Optional<OrderEntity> getOrder(Integer id) {
        return orderRepository.findById(id);
    }
}

