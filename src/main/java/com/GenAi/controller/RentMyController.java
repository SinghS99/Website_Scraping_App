package com.GenAi.controller;

import com.GenAi.model.dto.PlatformListingDTO;
import com.GenAi.service.RentMyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/rentmy")
public class RentMyController {

    @Autowired
    private RentMyService rentMyService;

    @GetMapping("/products")
    public List<PlatformListingDTO> getProducts() throws IOException {
        return rentMyService.fetchProducts();
    }
}

