package com.GenAi.service;

import com.GenAi.model.Cart;
import com.GenAi.model.CartItem;
import com.GenAi.model.Product;
import com.GenAi.model.User;
import com.GenAi.model.dto.CartDTO;
import com.GenAi.model.dto.CartItemDTO;
import com.GenAi.repo.CartRepo;
import com.GenAi.repo.ProductRepo;
import com.GenAi.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
public class CartService {
    @Autowired private CartRepo cartRepo;
    @Autowired private ProductRepo productRepo;

    public CartDTO addItem(Long userId, int productId, int qty) {
        // Find existing cart
        Cart cart = cartRepo.findByUserId(userId);

        // If no cart exists, create one
        if (cart == null) {
            cart = new Cart();
            cart.setUserId(userId);              // set userId directly
            cart.setItems(new ArrayList<>());    // initialize items list
        }

        // Find product
        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Create new cart item
        CartItem item = new CartItem();
        item.setCart(cart);
        item.setProduct(product);
        item.setQuantity(qty);

        // Add to cart
        cart.getItems().add(item);

        // Save cart (cascade ensures CartItem is saved too)
        Cart saved = cartRepo.save(cart);

        // Map to DTO
        return toCartDTO(saved);
    }



    public CartDTO viewCart(Long userId) {
        Cart cart = cartRepo.findByUserId(userId);
        if (cart == null) {
            cart = new Cart();
            cart.setId(userId);          // use userId field, not cart.setId()
            cart.setItems(new ArrayList<>());
            cartRepo.save(cart);
        }
        return toCartDTO(cart);
    }


    private CartDTO toCartDTO(Cart cart) {
        CartDTO dto = new CartDTO();
        dto.setId(cart.getId());
        dto.setUserId(cart.getId()); // assuming you store userId directly
        dto.setItems(cart.getItems().stream().map(ci -> {
            CartItemDTO itemDTO = new CartItemDTO();
            itemDTO.setId(ci.getId());
            itemDTO.setProductId(ci.getProduct().getItemId());
            itemDTO.setProductTitle(ci.getProduct().getItemProfile());
            itemDTO.setQuantity(ci.getQuantity());
            itemDTO.setPrice(ci.getProduct().getPrice());
            return itemDTO;
        }).collect(Collectors.toList()));
        return dto;
    }
}
