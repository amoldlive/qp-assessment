package com.grocery.app.service;

import com.grocery.app.entity.Order;
import com.grocery.app.entity.Product;

import java.util.List;

public interface UserService {
    public List<Product> getAllProduct();
     List<Order> placeOrder(List<Order> orderList);
}
