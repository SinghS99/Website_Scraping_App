package com.GenAi.model;

import jakarta.persistence.*;

@Entity
@Table(name="platform_listing")
public class PlatformListing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String platform;   // Rentomojo, Cityfurnish
    private String url;
    private double price;
    private String unit;       // per month, per week, etc.

    @ManyToOne
    @JoinColumn(name = "product_id")   // FK to Product.itemId
    private Product product;

    // getters/setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "PlatformListing{" +
                "id=" + id +
                ", platform='" + platform + '\'' +
                ", url='" + url + '\'' +
                ", price=" + price +
                ", unit='" + unit + '\'' +
                ", product=" + product +
                '}';
    }
}
