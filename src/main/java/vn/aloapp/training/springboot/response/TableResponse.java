package vn.aloapp.training.springboot.response;

import java.util.Date;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonProperty;

import vn.aloapp.training.springboot.entity.Table;

public class TableResponse {
	
	private int id;
	
	@JsonProperty("restaurant_id")
	private int restaurantId;
	
	@JsonProperty("restaurant_brand_id")
	private int restaurantBrandId;
	
	@JsonProperty("branch_id")
	private int branchId;
	
	@JsonProperty("area_id")
	private int areaId;

	@JsonProperty("name")
	private String name;
	
	@JsonProperty("description")
	private String description;
	
	@JsonProperty("total_slot")
	private int totalSlot;
	
	@JsonProperty("status")
	private int status;
	
	@JsonProperty("created_at")
	private Date createdAt;
	
	public TableResponse() {
		// TODO Auto-generated constructor stub
	}
	
	public TableResponse(Table entity) {
		this.id = entity.getId();
		this.name = entity.getName();
		this.restaurantId = entity.getRestaurantId();
		this.restaurantBrandId = entity.getRestaurantBrandId();
		this.branchId = entity.getBranchId();
		this.areaId = entity.getAreaId();
		this.description = entity.getDescription();
		this.totalSlot = entity.getTotalSlot();
		this.status = entity.getStatus();
		this.createdAt = entity.getCreatedAt();
	}
	
	public List<TableResponse> mapToList(List<Table> entities) {
		return entities.stream().map(x -> new TableResponse(x)).collect(Collectors.toList());
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
