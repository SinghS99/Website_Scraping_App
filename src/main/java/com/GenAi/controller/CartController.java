package com.GenAi.controller;

import com.GenAi.model.dto.CartDTO;
import com.GenAi.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @PostMapping("/add")
    public CartDTO addItem(@RequestParam Long userId,
                           @RequestParam int productId,
                           @RequestParam int qty) {
        return cartService.addItem(userId, productId, qty);
    }

    @GetMapping("/{userId}")
    public CartDTO viewCart(@PathVariable Long userId) {
        return cartService.viewCart(userId);
    }
}
