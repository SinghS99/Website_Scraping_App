package com.GenAi.service;

import com.GenAi.model.Product;
import com.GenAi.model.ProductMapper;
import com.GenAi.model.dto.ProductDTO;
import com.GenAi.repo.ProductRepo;
import com.GenAi.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    public ProductRepo repo;
    @Autowired
    public UserRepo userRepo;

    // fetch all products (from DB) and map to DTOs
    public List<ProductDTO> fetchAndSaveProducts() {
        return repo.findAll().stream()
                .map(ProductMapper::toDTO)
                .collect(Collectors.toList());
    }

    // add a product
    public ProductDTO addProduct(ProductDTO dto) {
        Product entity = ProductMapper.toEntity(dto);
        Product saved = repo.save(entity);
        return ProductMapper.toDTO(saved);
    }

    // return all products
    public List<ProductDTO> getAllProducts() {
        return repo.findAll().stream()
                .map(ProductMapper::toDTO)
                .collect(Collectors.toList());
    }

    // return single product
    public ProductDTO getProduct(int itemId) {
        return repo.findById(itemId)
                .map(ProductMapper::toDTO)
                .orElse(null);
    }

    // update product
    public ProductDTO updateProducts(ProductDTO dto) {
        Product entity = ProductMapper.toEntity(dto);
        Product updated = repo.save(entity);
        return ProductMapper.toDTO(updated);
    }

    // delete product
    public void deleteProduct(int itemId) {
        repo.deleteById(itemId);
    }

    // search products by keyword
    public List<ProductDTO> search(String keyword) {
        return repo.findByItemProfileContainingOrItemDescContaining(keyword, keyword)
                .stream()
                .map(ProductMapper::toDTO)
                .collect(Collectors.toList());
    }
}


/*public void laod() {
    List<Product> Products = new ArrayList<>(Arrays.asList(
            new Product(1, "D SOFA", "Excellent,Good,Average", 2,
                    List.of("Single", "Double", "Triple", "Family pack")),
            new Product(2, "Refrigrator", "Excellent,Good,Average", 3,
                    List.of("Single", "Double", "Triple", "Family pack")),
            new Product(3, "MicroOven", "Excellent,Good,Average", 4,
                    List.of("Single", "Double", "Triple", "Family pack")),
            new Product(4, "Bed", "Excellent,Good,Average", 5,
                    List.of("Single", "Double", "Triple", "Family pack")),
            new Product(5, "KingsSize Bed", "Excellent,Good,Average", 3,
                    List.of("Single", "Double", "Triple", "Family pack"))
    ));
    repo.saveAll(Products);
}*/

/*from external API call data laods
public List<Product> fetchAndSaveProducts() {
    String url = "https://jsonplaceholder.typicode.com/users";

    // Response is wrapped in an object with "products" key
    Map<String, Object> response = restTemplate.getForObject(url, Map.class);

    List<Map<String, Object>> products = (List<Map<String, Object>>) response.get("products");

    List<Product> entities = products.stream().map(p -> {
        Product product = new Product();
        product.setItemId((Integer) p.get("id"));
        product.setItemProfile((String) p.get("title"));
        product.setItemDesc((String) p.get("description"));
        product.setItemQuantity((Integer) p.get("stock"));
        product.setItemType((List<String>) p.get("images")); // or category/brand if you prefer
        return product;
    }).collect(Collectors.toList());

    return repo.saveAll(entities);
}*/
