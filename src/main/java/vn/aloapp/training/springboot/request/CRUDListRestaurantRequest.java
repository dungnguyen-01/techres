package vn.aloapp.training.springboot.request;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CRUDListRestaurantRequest {

	@Valid
	@NotNull(message = "Không được truyền danh sách null")
	@JsonProperty("list_object")
	private List<CRUDRestaurantRequest> listObject;

	public List<CRUDRestaurantRequest> getListObject() {
		return listObject;
	}

	public void setListObject(List<CRUDRestaurantRequest> listObject) {
		this.listObject = listObject;
	}

}
