package com.grocery.app.service;

import com.grocery.app.entity.Inventory;
import com.grocery.app.entity.Order;
import com.grocery.app.entity.Product;
import com.grocery.app.entity.StockStatus;
import com.grocery.app.repository.OrderRepository;
import com.grocery.app.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl  implements UserService{
    private final ProductRepository productRepository;
    private  final OrderRepository orderRepository;
    public UserServiceImpl(ProductRepository productRepository, OrderRepository orderRepository) {
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    public List<Product> getAllProduct(){
       return productRepository.findAll().stream().filter(product -> product.getInventory().getStockStatus()==StockStatus.IN_STOCK).toList();
    }

    public List<Order> placeOrder(List<Order> orderList){
      return  orderList.stream().map(order -> {
            Product product=productRepository.findById(order.getOrderId()).get();
            Inventory inventory= product.getInventory();
            if(inventory.getQuantity()>=order.getQuantity()){
                inventory.setQuantity(inventory.getQuantity()-order.getQuantity());
                if(inventory.getQuantity()==0){
                    inventory.setStockStatus(StockStatus.OUT_OF_STOCK);
                }
                order.setStatus(true);
                orderRepository.save(order);
                return order;
            }else{
                order.setStatus(false);
                orderRepository.save(order);
                return  order;
            }
        }).toList();
     }


}
