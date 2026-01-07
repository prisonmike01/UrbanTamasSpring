package com.UrbanTamas.demo.data.dto.request;

import lombok.Data;

@Data
public class CartRequest {
    private Integer productId;
    private int quantity;
}
