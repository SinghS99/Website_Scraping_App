package com.GenAi.service;

import com.GenAi.model.ProductMapper;
import com.GenAi.model.dto.PlatformListingDTO;
import com.GenAi.repo.PlatformListingRepo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.GenAi.model.ProductMapper.toEntity;

@Service
public class LiveComparisonService {

    @Autowired
    private PlatformListingRepo listingRepo;

    public PlatformListingDTO fetchRentomojoProduct(String productName, String url) throws IOException {
        Document doc = Jsoup.connect(url)
                .userAgent("Mozilla/5.0")
                .timeout(10_000)
                .get();

        Elements priceElements = doc.select("span.price");
        if (priceElements.isEmpty()) {
            throw new RuntimeException("Price not found on Rentomojo page: " + url);
        }

        String priceText = priceElements.first().text(); // e.g. "₹ 734 /mo"
        String numeric = priceText.replaceAll("[^0-9]", "");
        double price = numeric.isEmpty() ? 0 : Double.parseDouble(numeric);

        PlatformListingDTO dto = new PlatformListingDTO("Rentomojo", productName, price, "per month", url);

        // Save to DB
        listingRepo.save(ProductMapper.toEntity1(dto));

        return dto;
    }

    public PlatformListingDTO fetchCityfurnishProduct(String productName, String url) throws IOException {
        Document doc = Jsoup.connect(url)
                .userAgent("Mozilla/5.0")
                .timeout(10_000)
                .get();

        // Look for the price wrapper
        Elements priceElements = doc.select("div[class*=price_row_wrapper], div[class*=card_price_wrap]");
        if (priceElements.isEmpty()) {
            throw new RuntimeException("Price not found on Cityfurnish page: " + url);
        }

        String priceText = priceElements.text(); // e.g. "₹219/mo"
        String numeric = priceText.replaceAll("[^0-9]", "");
        double price = numeric.isEmpty() ? 0 : Double.parseDouble(numeric);

        return new PlatformListingDTO("Cityfurnish", productName, price, "per month", url);
    }

    public List<PlatformListingDTO> fetchRentomojoCategoryApi(String productName, String city, String category, String query) throws JsonProcessingException {
        WebClient client = WebClient.builder()
                .baseUrl("https://rdp-prod.rentomojo.com")
                .defaultHeader(HttpHeaders.USER_AGENT, "Mozilla/5.0")
                .defaultHeader(HttpHeaders.ORIGIN, "https://www.rentomojo.com")
                .defaultHeader(HttpHeaders.REFERER, "https://www.rentomojo.com/" + city + "/furniture/" + query + "s-on-rent")
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Basic Mjl2bHFMZ1pLVVNJcTBWMG5Sc2JNOEZzU0FxOg==") // from DevTools
                .build();

        String payload = "{ \"pageType\": \"CATEGORY\", \"city\": \"" + city + "\", \"category\": \"" + category + "\", \"query\": \"" + query + "\" }";

        String response = client.post()
                .uri("/v1/page")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(payload)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response);

        List<PlatformListingDTO> listings = new ArrayList<>();
        for (JsonNode item : root.get("data").get("items")) {
            String name = item.get("title").asText();
            double price = item.get("monthlyRent").asDouble();
            String url = "https://www.rentomojo.com" + item.get("url").asText();

            listings.add(new PlatformListingDTO("Rentomojo", name, price, "per month", url));
        }

        return listings;
    }


}


