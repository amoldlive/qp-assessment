package com.grocery.app.service;

import com.grocery.app.entity.Inventory;
import com.grocery.app.entity.Product;
import com.grocery.app.repository.InventoryRepository;
import com.grocery.app.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

   private final ProductRepository productRepository;
    private final InventoryRepository inventoryRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, InventoryRepository inventoryRepository) {
        this.productRepository = productRepository;
        this.inventoryRepository = inventoryRepository;
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @SneakyThrows
    @Override
    public Product removeProduct(Long productId) {
        Optional<Product> product = productRepository.findById(productId);
        if (product.isPresent()) {
            productRepository.deleteById(productId);
        } else {
            throw new Exception("product not found");
        }
        return product.get();
    }

    @SneakyThrows
    @Override
    public Product updateProduct(Product product) {
        Optional<Product> productFromDb = productRepository.findById(product.getProductId());
        Product productEntityFromDB = null;
        if (productFromDb.isPresent()) {
            productEntityFromDB = productFromDb.get();
            if (product.getProductBrand() != null) productEntityFromDB.setProductBrand(product.getProductBrand());

            if (product.getProductName() != null) productEntityFromDB.setProductName(product.getProductName());

            if (product.getProductPrice() != null) productEntityFromDB.setProductPrice(product.getProductPrice());

            if (product.getProductPictureUrl() != null)
                productEntityFromDB.setProductPictureUrl(product.getProductPictureUrl());


            Inventory inventory = productEntityFromDB.getInventory();

            if (product.getInventory().getQuantity() != 0) inventory.setQuantity(product.getInventory().getQuantity());

            if (product.getInventory().getStockStatus() != null)
                inventory.setStockStatus(product.getInventory().getStockStatus());

            productEntityFromDB.setInventory(inventory);

            productRepository.save(productEntityFromDB);
        } else {
            throw new Exception("Product not found");
        }
        return productEntityFromDB;
    }

    @Override
    public List<Inventory> getAllInventory() {
        return inventoryRepository.findAll();
    }

    @SneakyThrows
    @Override
    public Inventory updateInventory(Inventory inventory) {
        Optional<Inventory> inventoryFrmoDb = inventoryRepository.findById(inventory.getInventoryId());

        if(inventoryFrmoDb.isPresent()){
            Inventory entity= inventoryFrmoDb.get();
            entity.setQuantity(inventory.getQuantity());
            entity.setStockStatus(inventory.getStockStatus());
        }else{
            throw new Exception("Inventory not found");
        }
        return inventoryFrmoDb.get();
    }

}
