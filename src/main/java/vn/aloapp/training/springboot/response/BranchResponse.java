package vn.aloapp.training.springboot.response;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonProperty;

import vn.aloapp.training.springboot.entity.Branch;

public class BranchResponse {

	private int id;
	
	@JsonProperty("restaurant_id")
	private int restaurantId;
	
	@JsonProperty("restaurant_brand_id")
	private int restaurantBrandId;
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("street_name")
	private String streetName;
	
	@JsonProperty("address_full_text")
	private String addressFullText;
	
	@JsonProperty("phone_number")
	private String phoneNumber;
	
	@JsonProperty("status")
	private int status;
	
	@JsonProperty("created_at")
	private Date createdAt;
	
	public BranchResponse() {
	}
	
	public BranchResponse(Branch entity) {
		this.id = entity.getId();
		this.name = entity.getName();
		this.restaurantBrandId = entity.getRestaurantBrandId();
		this.restaurantId = entity.getRestaurantId();
		this.streetName = entity.getStreetName();
		this.addressFullText = entity.getAddressFullText();
		this.phoneNumber = entity.getPhoneNumber();
		this.status = entity.getStatus();
		this.createdAt = entity.getCreatedAt();
	}
	
	public List<BranchResponse> mapToList(List<Branch> entities) {
		return entities.stream().map(x -> new BranchResponse(x)).collect(Collectors.toList());
	}
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}

	public int getRestaurantBrandId() {
		return restaurantBrandId;
	}

	public void setRestaurantBrandId(int restaurantBrandId) {
		this.restaurantBrandId = restaurantBrandId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getAddressFullText() {
		return addressFullText;
	}

	public void setAddressFullText(String addressFullText) {
		this.addressFullText = addressFullText;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
	
}
