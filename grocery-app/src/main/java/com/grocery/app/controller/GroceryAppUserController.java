package com.grocery.app.controller;

import com.grocery.app.entity.Order;
import com.grocery.app.entity.Product;
import com.grocery.app.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class GroceryAppUserController {

    private final UserService userService;

    public GroceryAppUserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/product")
    public @ResponseBody List<Product> getAllProducts() {
        return userService.getAllProduct();
    }

    @PostMapping("/order")
    public @ResponseBody List<Order> placeOrder(List<Order> orders) {
        return userService.placeOrder(orders);
    }


}
