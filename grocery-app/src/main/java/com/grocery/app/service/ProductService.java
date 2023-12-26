package com.grocery.app.service;

import com.grocery.app.entity.Inventory;
import com.grocery.app.entity.Product;

import java.util.List;

public interface ProductService {
    Product addProduct(Product product);
    List<Product> getAllProducts();

    Product removeProduct(Long productId);
    Product updateProduct(Product product);

    List<Inventory> getAllInventory();
    Inventory updateInventory(Inventory inventory);
}
