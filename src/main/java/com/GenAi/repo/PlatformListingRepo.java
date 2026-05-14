package com.GenAi.repo;

import com.GenAi.model.PlatformListing;
import com.GenAi.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlatformListingRepo extends JpaRepository<PlatformListing, Long> {
    // Find all listings for a given product
    List<PlatformListing> findByProduct(Product product);

    // Or directly by product name (through the relationship)
    @Query("SELECT l FROM PlatformListing l WHERE LOWER(l.product.itemProfile) = LOWER(:name)")
    List<PlatformListing> findByProductNameIgnoreCase(@Param("name") String name);
}
