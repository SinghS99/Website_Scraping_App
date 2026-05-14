package com.GenAi.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/*@Data
@NoArgsConstructor
@AllArgsConstructor*/
@Entity
@Table(name = "product")   // explicitly align with data.sql
public class Product {

	@Id
	private int itemId;

	private String itemProfile;
	private String itemDesc;
	private Integer itemQuantity;
	@ElementCollection
	private List<String> itemType;
	private double price; // ✅ add price
	// ✅ New relation: one product can have many platform listings
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<PlatformListing> listings = new ArrayList<>();

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public String getItemProfile() {
		return itemProfile;
	}

	public void setItemProfile(String itemProfile) {
		this.itemProfile = itemProfile;
	}

	public String getItemDesc() {
		return itemDesc;
	}

	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}

	public Integer getItemQuantity() {
		return itemQuantity;
	}

	public void setItemQuantity(Integer itemQuantity) {
		this.itemQuantity = itemQuantity;
	}

	public List<String> getItemType() {
		return itemType;
	}

	public void setItemType(List<String> itemType) {
		this.itemType = itemType;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Product{" +
				"itemId=" + itemId +
				", itemProfile='" + itemProfile + '\'' +
				", itemDesc='" + itemDesc + '\'' +
				", itemQuantity=" + itemQuantity +
				", itemType=" + itemType +
				", price=" + price +
				'}';
	}
}