package vn.aloapp.training.springboot.response;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonProperty;

import vn.aloapp.training.springboot.entity.Area;

public class AreaResponse {

	private int id;

	private String name;

	@JsonProperty("restaurant_id")
	private int restaurantId;

	@JsonProperty("restaurant_brand_id")
	private int restaurantBrandId;

	@JsonProperty("branch_id")
	private int branchId;

	@JsonProperty("description")
	private String description;

	@JsonProperty("status")
	private int status;

	@JsonProperty("list")
	private ObjectList list;

	@JsonProperty("create_at")
	private Date createAt;

	public AreaResponse() {
	}

	public AreaResponse(Area entity) {
		this.id = entity.getId();
		this.name = entity.getName();
		this.restaurantId = entity.getRestaurantId();
		this.restaurantBrandId = entity.getRestaurantBrandId();
		this.branchId = entity.getBranchId();
		this.description = entity.getDescription();
		this.status = entity.getStatus();
		this.list = new ObjectList<>();
		this.createAt = entity.getCreatedAt();
	}

	public List<AreaResponse> mapToList(List<Area> entities) {
		return entities.stream().map(x -> new AreaResponse(x)).collect(Collectors.toList());
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public ObjectList getList() {
		return list;
	}

	public void setList(ObjectList list) {
		this.list = list;
	}

}
