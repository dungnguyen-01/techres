package vn.aloapp.training.springboot.request;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CRUDAreaRequest extends BaseRequest{
	
	
	@NotEmpty(message = "Ten không được để trống")
	@JsonProperty("name")
	private String name;
	
	@NotEmpty(message = "Mã nhà hàng không được để trống")
	@JsonProperty("restaurant_id")
	private int restaurantId;
	
	@NotEmpty(message = "Mã thương hiệu không được để trống")
	@JsonProperty("restaurant_brand_id")
	private int restaurantBrandId;
	
	@NotEmpty(message = "Mã chi nhánh không được để trống")
	@JsonProperty("branch_id")
	private int branchId;
	
	private String description;
	
	private int status;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}	  	
	

	
}
