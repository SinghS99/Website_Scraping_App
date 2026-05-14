package com.GenAi.model.dto;

public class PlatformListingDTO {
    private String platform;   // Rentomojo, Cityfurnish
    private String productName;
    private double price;      // normalized price
    private String unit;       // per month, per week, etc.
    private String url;        // external product URL

    public PlatformListingDTO(String platform, String productName, double price, String unit, String url) {
        this.platform = platform;
        this.productName = productName;
        this.price = price;
        this.unit = unit;
        this.url = url;
    }

    // getters and setters
    public String getPlatform() { return platform; }
    public void setPlatform(String platform) { this.platform = platform; }

    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public String getUnit() { return unit; }
    public void setUnit(String unit) { this.unit = unit; }

    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }
}
