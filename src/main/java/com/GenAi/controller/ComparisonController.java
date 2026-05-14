package com.GenAi.controller;

import com.GenAi.model.PlatformListing;
import com.GenAi.model.dto.PlatformListingDTO;
import com.GenAi.repo.PlatformListingRepo;
import com.GenAi.service.LiveComparisonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/compare")
public class ComparisonController {
    @Autowired
    private LiveComparisonService liveService;

    @GetMapping("/{productName}")
    public List<PlatformListingDTO> compare(@PathVariable String productName) throws IOException {
        List<PlatformListingDTO> listings = new ArrayList<>();

        if (productName.equalsIgnoreCase("Sofa")) {
            listings.add(liveService.fetchRentomojoProduct("Sofa",
                    "https://www.rentomojo.com/kolkata/furniture/rent-heathcliff-3-seater-couch"));

            listings.add(liveService.fetchCityfurnishProduct("Sofa",
                   "https://cityfurnish.com/things/3976/erica-single-seater-sofa"));
        }

        // Add more mappings for Bed, Refrigerator, etc.

        return listings.stream()
                .sorted(Comparator.comparingDouble(PlatformListingDTO::getPrice))
                .collect(Collectors.toList());
    }
}


//@Autowired
//private PlatformListingRepo listingRepo;
//
//@GetMapping("/{productName}")
//public List<PlatformListingDTO> compare(@PathVariable String productName) {
//    List<PlatformListing> listings = listingRepo.findByProductNameIgnoreCase(productName);
//    return listings.stream()
//            .map(l -> new PlatformListingDTO(
//                    l.getPlatform(),
//                    l.getProduct().getItemProfile(),
//                    normalizePrice(l),
//                    l.getUnit(),
//                    l.getUrl()))
//            .sorted(Comparator.comparingDouble(PlatformListingDTO::getPrice))
//            .collect(Collectors.toList());
//}
//
//private double normalizePrice(PlatformListing l) {
//    switch (l.getUnit()) {
//        case "per day": return l.getPrice() * 30;
//        case "per week": return l.getPrice() * 4;
//        case "per month": return l.getPrice();
//        default: return l.getPrice();
//    }
//}
