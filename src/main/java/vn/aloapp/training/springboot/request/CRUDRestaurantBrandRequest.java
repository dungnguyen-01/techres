package vn.aloapp.training.springboot.request;


import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CRUDRestaurantBrandRequest {
	
	@JsonProperty("restaurant_id")
	private int restaurantId;
	
	@NotEmpty(message = "Tên không được để trống")
	@JsonProperty("name")
	private String name;
	
	@NotEmpty(message = "Logo url không được để trống")
	@JsonProperty("logo_url")
	private String logoUrl;
	
	@NotEmpty(message = "banner không được để trống")
	@JsonProperty("banner")
	private String banner;
	
	@NotEmpty(message = "Mô tả không được để trống")
	@JsonProperty("description")
	private String description;

	public int getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	public String getBanner() {
		return banner;
	}

	public void setBanner(String banner) {
		this.banner = banner;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
