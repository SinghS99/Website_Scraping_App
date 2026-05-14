package com.GenAi.service;

import com.GenAi.model.Cart;
import com.GenAi.model.CartItem;
import com.GenAi.model.Order;
import com.GenAi.model.OrderItem;
import com.GenAi.model.dto.OrderDTO;
import com.GenAi.model.dto.OrderItemDTO;
import com.GenAi.repo.CartRepo;
import com.GenAi.repo.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class OrderService {
    @Autowired private OrderRepo orderRepo;
    @Autowired private CartRepo cartRepo;

    public OrderDTO placeOrder(Long userId) {
        Cart cart = cartRepo.findByUserId(userId);
        if (cart == null || cart.getItems().isEmpty()) {
            throw new RuntimeException("No cart found or cart is empty for user " + userId);
        }
        Order order = new Order();
        order.setUserId(cart.getUserId());

        double total = 0;
        for (CartItem ci : cart.getItems()) {
            OrderItem oi = new OrderItem();
            oi.setOrder(order);
            oi.setProduct(ci.getProduct());
            oi.setQuantity(ci.getQuantity());
            oi.setPrice(ci.getProduct().getPrice());
            order.getItems().add(oi);
            total += ci.getQuantity() * ci.getProduct().getPrice();
        }
        order.setTotalAmount(total);
        order.setStatus("PENDING");

        Order saved = orderRepo.save(order);
        return toOrderDTO(saved);
    }

    private OrderDTO toOrderDTO(Order order) {
        OrderDTO dto = new OrderDTO();
        dto.setId(order.getId());
        dto.setUserId(order.getUserId());
        dto.setTotalAmount(order.getTotalAmount());
        dto.setStatus(order.getStatus());
        dto.setItems(order.getItems().stream().map(oi -> {
            OrderItemDTO itemDTO = new OrderItemDTO();
            itemDTO.setId(oi.getId());
            itemDTO.setProductId(oi.getProduct().getItemId());
            itemDTO.setProductTitle(oi.getProduct().getItemProfile());
            itemDTO.setQuantity(oi.getQuantity());
            itemDTO.setPrice(oi.getPrice());
            return itemDTO;
        }).collect(Collectors.toList()));
        return dto;
    }
}
