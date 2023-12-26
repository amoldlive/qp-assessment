package com.grocery.app.controller;

import com.grocery.app.entity.Inventory;
import com.grocery.app.entity.Product;
import com.grocery.app.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class GroceryAppAdminController {
    private final ProductService productService;

    @Autowired
    public GroceryAppAdminController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/product")
    public @ResponseBody Product addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    @GetMapping("/product")
    public @ResponseBody List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @DeleteMapping("/product")
    public Product removeProduct(Long productId) {
        return productService.removeProduct(productId);
    }

    @PutMapping("/product")
    public Product updateProduct(Product product)  {
        return productService.updateProduct(product);
    }

    @PutMapping("/inventory")
    public Inventory updateInventory(Inventory inventory) {
        return productService.updateInventory(inventory);
    }

    @GetMapping("/inventory")
    public List<Inventory> getAllInventory()  {
        return productService.getAllInventory();
    }
}
