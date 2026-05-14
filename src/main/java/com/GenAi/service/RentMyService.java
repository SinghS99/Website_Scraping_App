package com.GenAi.service;

import com.GenAi.model.ProductMapper;
import com.GenAi.model.dto.PlatformListingDTO;
import com.GenAi.repo.PlatformListingRepo;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class RentMyService {

    @Autowired
    private
    WebClient rentMyWebClient;

    @Autowired
    private PlatformListingRepo listingRepo;

    public List<PlatformListingDTO> fetchProducts() throws IOException {
        String payload = "{\"page\":1,\"limit\":10}";

        String response = rentMyWebClient.post()
            .uri("/products/online")
            .bodyValue(payload)
            .retrieve()
            .bodyToMono(String.class)
            .block();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response);

        List<PlatformListingDTO> listings = new ArrayList<>();
        for (JsonNode item : root.get("data")) {
            PlatformListingDTO dto = new PlatformListingDTO(
                "RentMy",
                item.get("name").asText(),
                item.get("price").asDouble(),
                "per month",
                item.get("url").asText()
            );

            // Save to DB
            listingRepo.save(ProductMapper.toEntity1(dto));
            listings.add(dto);
        }

        return listings;
    }
}
