package com.GenAi.controller;

import com.GenAi.model.User;
import com.GenAi.model.dto.ProductDTO;
import com.GenAi.service.ProductService;
import com.GenAi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {

    @Autowired
    private ProductService service;
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User registerUsers(@RequestBody User user){
        return userService.saveUser(user);
    }

    @GetMapping("/search/{keyword}")
    public List<ProductDTO> productsByKeyword(@PathVariable String keyword){
        return service.search(keyword);
    }

    @GetMapping("/products")
    public List<ProductDTO> getAllProducts() {
        return service.getAllProducts();
    }

    @GetMapping("/{id}")
    public ProductDTO getProduct(@PathVariable int id) {
        return service.getProduct(id);
    }

    @PostMapping("/product")
    public ProductDTO addProduct(@RequestBody ProductDTO dto){
        return service.addProduct(dto);
    }

    @PutMapping("/{id}")
    public ProductDTO updateProduct(@PathVariable int id, @RequestBody ProductDTO dto){
        dto.setId(id);
        return service.updateProducts(dto);
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable int id){
        service.deleteProduct(id);
        return "Deleted";
    }

    @GetMapping("/load")
    public String loaddata(){
        List<ProductDTO> products = service.fetchAndSaveProducts();
        return "Products loaded successfully! Count: " + products.size();
    }

}