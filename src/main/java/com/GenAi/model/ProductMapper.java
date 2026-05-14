package com.GenAi.model;

import com.GenAi.model.dto.PlatformListingDTO;
import com.GenAi.model.dto.ProductDTO;

public class ProductMapper {

    public static ProductDTO toDTO(Product entity) {
        ProductDTO dto = new ProductDTO();
        dto.setId(entity.getItemId());
        dto.setTitle(entity.getItemProfile());
        dto.setDescription(entity.getItemDesc());
        dto.setStock(entity.getItemQuantity());
        dto.setImages(entity.getItemType());
        dto.setPrice((int) entity.getPrice()); // ✅ map price
        return dto;
    }

    public static Product toEntity(ProductDTO dto) {
        Product entity = new Product();
        entity.setItemId(dto.getId());
        entity.setItemProfile(dto.getTitle());
        entity.setItemDesc(dto.getDescription());
        entity.setItemQuantity(dto.getStock());
        entity.setItemType(dto.getImages());
        entity.setPrice(dto.getPrice()); // ✅ map price
        return entity;
    }
    public  static PlatformListing toEntity1(PlatformListingDTO dto) {
        PlatformListing entity = new PlatformListing();
        entity.setPlatform(dto.getPlatform());
        entity.setPrice(dto.getPrice());
        entity.setUnit(dto.getUnit());
        entity.setUrl(dto.getUrl());
        // If you have productId or other fields, set them here
        return entity;
    }

}
