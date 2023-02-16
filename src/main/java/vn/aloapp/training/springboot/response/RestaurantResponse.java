package vn.aloapp.training.springboot.response;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonProperty;

import vn.aloapp.training.springboot.entity.Restaurant;

public class RestaurantResponse {

	private int id;

	private String name;

	@JsonProperty("email")
	private String email;

	@JsonProperty("phone")
	private String phone;

	@JsonProperty("info")
	private String info;

	@JsonProperty("address")
	private String address;

	@JsonProperty("logo")
	private String logo;
	
	@JsonProperty("status")
	private int status;
	
	@JsonProperty("list")
	private ObjectList list;

	@JsonProperty("created_at")
	private Date createdAt;

	public RestaurantResponse() {
	}

	public RestaurantResponse(Restaurant entity) {
		this.id = entity.getId();
		this.name = entity.getName();
		this.email = entity.getEmail();
		this.phone = entity.getPhone();
		this.info = entity.getInfo();
		this.address = entity.getAddress();
		this.logo = entity.getLogo();
		this.status = entity.getStatus();
		this.list = new ObjectList();
		this.createdAt = entity.getCreatedAt();
	}

	public List<RestaurantResponse> mapToList(List<Restaurant> entiies) {
		return entiies.stream().map(x -> new RestaurantResponse(x)).collect(Collectors.toList());
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	

	public ObjectList getList() {
		return list;
	}

	public void setList(ObjectList list) {
		this.list = list;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

}
