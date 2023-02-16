package vn.aloapp.training.springboot.response;

import java.util.Date;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonProperty;

import vn.aloapp.training.springboot.entity.RestaurantBrand;

public class RestaurantBrandResponse {

	private int id;
	
	@JsonProperty("restaurant_id")
	private int restaurantId;
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("logo_url")
	private String logoUrl;
	
	@JsonProperty("banner")
	private String banner;
	
	@JsonProperty("description")
	private String description;
	
	@JsonProperty("status")
	private int status;
	
	@JsonProperty("list")
	private ObjectList list;
	

	@JsonProperty("created_at")
	private Date createdAt;
	
//	@JsonProperty("update_at")
//	private Date updateAt;
	
	public RestaurantBrandResponse() {
	
	}
	
	public RestaurantBrandResponse(RestaurantBrand entity) {
		this.id = entity.getId();
		this.name = entity.getName();
		this.restaurantId = entity.getRestaurantId();
		this.logoUrl = entity.getLogoUrl();
		this.banner = entity.getBanner();
		this.description = entity.getDescription();
		this.status = entity.getStatus();
		this.list = new ObjectList();
		this.createdAt = entity.getCreatedAt();
	}
	
	public List<RestaurantBrandResponse> mapToList(List<RestaurantBrand> entities) {
		return entities.stream().map(x -> new RestaurantBrandResponse(x)).collect(Collectors.toList());
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

	public ObjectList getList() {
		return list;
	}

	public void setList(ObjectList list) {
		this.list = list;
	}

	
	
}
