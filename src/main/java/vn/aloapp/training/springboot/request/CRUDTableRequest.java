package vn.aloapp.training.springboot.request;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CRUDTableRequest extends BaseRequest{

	@NotNull(message = "restaurant_id không được để trống")
	@JsonProperty("restaurant_id")
	private int restaurantId;
	
	@NotNull(message = "restaurant_brand_id không được để trống")
	@JsonProperty("restaurant_brand_id")
	private int restaurantBrandId;
	
	@NotNull(message = "Mã chi nhánh không được để trống")
	@JsonProperty("branch_id")
	private int branchId;
	
	@NotNull(message = "Mã khu vực không được để trống")
	@JsonProperty("area_id")
	private int areaId;

	@NotEmpty(message = "Tên không được để trống")
	private String name;
	
	private String description;
	
	@JsonProperty("total_slot")
	private int totalSlot;
	

	private int status;

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

	public int getAreaId() {
		return areaId;
	}

	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getTotalSlot() {
		return totalSlot;
	}

	public void setTotalSlot(int totalSlot) {
		this.totalSlot = totalSlot;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	
}
