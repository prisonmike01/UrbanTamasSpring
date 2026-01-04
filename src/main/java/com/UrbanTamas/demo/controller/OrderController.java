package com.UrbanTamas.demo.controller;

import com.UrbanTamas.demo.data.entity.OrderEntity;
import com.UrbanTamas.demo.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @GetMapping("/:id")
    Optional<OrderEntity> getOrder(@RequestParam Integer orderId) {
        return orderService.getOrder(orderId);
    }
}