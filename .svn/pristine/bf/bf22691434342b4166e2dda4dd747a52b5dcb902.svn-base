package vn.aloapp.training.springboot.request;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CRUDAreaRequest extends BaseRequest{
	
	@NotEmpty(message = "Mật khẩu không được để trống")
	private String name;
	
	@NotEmpty(message = "Mật khẩu mới không được để trống")
	@JsonProperty("short_description")
	private String shortDescription;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the shortDescription
	 */
	public String getShortDescription() {
		return shortDescription;
	}

	/**
	 * @param shortDescription the shortDescription to set
	 */
	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}
}
