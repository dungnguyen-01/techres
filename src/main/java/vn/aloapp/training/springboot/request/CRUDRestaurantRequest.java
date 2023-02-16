package vn.aloapp.training.springboot.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CRUDRestaurantRequest {

	@NotEmpty(message = "Tên không được để trống")
	private String name;

	@NotNull(message = "email không được null")
	@JsonProperty("email")
	private String email;

	@NotEmpty(message = "Số điện thoại không được để trống")
	@JsonProperty("phone")
	private String phone;

	@NotNull(message = "Thông tin không được null")
	@Length(max = 255, message = "Không được phép lớn hơn 255 kí tự")
	@JsonProperty("info")
	private String info;

	@NotNull(message = "Địa chỉ không được null")
	@JsonProperty("address")
	private String address;

	@NotNull(message = "Hình ảnh không được null")
	@JsonProperty("logo")
	private String logo;
	
	@Min(value = -1)
	@Max(value = 1)
	@JsonProperty("status")
	private int status;
	


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
	
	
	

}
