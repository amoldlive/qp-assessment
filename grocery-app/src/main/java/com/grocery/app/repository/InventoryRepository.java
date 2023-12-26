package com.grocery.app.repository;

import com.grocery.app.entity.Inventory;
import com.grocery.app.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory,Long> {
}
