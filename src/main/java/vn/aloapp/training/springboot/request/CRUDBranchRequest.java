package vn.aloapp.training.springboot.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CRUDBranchRequest extends BaseRequest{
	
	@NotEmpty(message = "Ten không được để trống")
	@JsonProperty("name")
	private String name;
	
	@NotEmpty(message = "Mã nhà hàng không được để trống")
	@JsonProperty("restaurant_id")
	private int restaurantId;
	
	@NotEmpty(message = "Mã thương hiệu không được để trống")
	@JsonProperty("restaurant_brand_id")
	private int restaurantBrandId;
	
	@NotEmpty(message = "Tên đường được để trống")
	@JsonProperty("street_name")
	private String streetName;
	
	@JsonProperty("Địa chỉ không được để trống.")
	private String addressFullText;
	
	@Pattern(regexp="(^$|[0-9]{10})")
	@JsonProperty("phone_number")
	private String phoneNumber;
	
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
	
	
}
