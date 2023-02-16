package vn.aloapp.training.springboot.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.aloapp.training.springboot.entity.RestaurantBrand;
import vn.aloapp.training.springboot.entity.StoreProcedureListResult;
import vn.aloapp.training.springboot.request.CRUDRestaurantBrandRequest;
import vn.aloapp.training.springboot.response.BaseResponse;
import vn.aloapp.training.springboot.response.RestaurantBrandResponse;
import vn.aloapp.training.springboot.service.RestaurantBrandService;
import vn.aloapp.training.springboot.service.RestaurantService;

@RestController
@RequestMapping("/api/v1/restaurant_brands")
public class RestaurantBrandController extends BaseResponse {

	@Autowired
	RestaurantBrandService restaurantBrandService;
	@Autowired
	private RestaurantService restaurantService;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/create", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseResponse> create(@Valid @RequestBody CRUDRestaurantBrandRequest wrapper)
			throws Exception {
		BaseResponse response = new BaseResponse();
		if (restaurantService.findById(wrapper.getRestaurantId()) == null) {

			response.setMessageError(HttpStatus.NOT_FOUND.name());
			response.setStatus(HttpStatus.NOT_FOUND);

		} else {
			RestaurantBrand restaurantBrand = restaurantBrandService.spUCreateRestaurantBrand(wrapper.getName(),
					wrapper.getBanner(), wrapper.getLogoUrl(), wrapper.getDescription(), wrapper.getRestaurantId());

			response.setData(new RestaurantBrandResponse(restaurantBrand));
		}

		return new ResponseEntity<BaseResponse>(response, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseResponse> getList() throws Exception {
		BaseResponse response = new BaseResponse();
		StoreProcedureListResult<RestaurantBrand> restaurants = restaurantBrandService.spGListRestaurantBrands();
		response.setData(new RestaurantBrandResponse().mapToList(restaurants.getResult()));
		return new ResponseEntity<BaseResponse>(response, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseResponse> getById(@PathVariable("id") Integer id) throws Exception {
		BaseResponse response = new BaseResponse();
		RestaurantBrand restaurantBrand = restaurantBrandService.findById(id);
		try {
			if (restaurantBrand != null) {
				response.setData(new RestaurantBrandResponse(restaurantBrand));
			} else {
				response.setStatus(HttpStatus.NOT_FOUND);
				response.setMessageError(HttpStatus.NOT_FOUND.name());
			}

		} catch (Exception e) {
			System.out.println(response);
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			response.setMessageError(e.getMessage());
		}

		return new ResponseEntity<BaseResponse>(response, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/{id}/update", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseResponse> update(@PathVariable("id") Integer id,
			@Valid @RequestBody CRUDRestaurantBrandRequest wrapper) throws Exception {

		BaseResponse response = new BaseResponse();
		RestaurantBrand restaurantBrand = restaurantBrandService.findById(id);
		if (restaurantBrand != null) {
			if (restaurantService.findById(wrapper.getRestaurantId()) == null) {

				response.setMessageError(HttpStatus.NOT_FOUND.name());
				response.setStatus(HttpStatus.NOT_FOUND);

			} else {
				restaurantBrand.setBanner(wrapper.getBanner());
				restaurantBrand.setName(wrapper.getName());
				restaurantBrand.setLogoUrl(wrapper.getLogoUrl());
				restaurantBrand.setDescription(wrapper.getDescription());
				restaurantBrand.setRestaurantId(wrapper.getRestaurantId());

				restaurantBrandService.spUpdateRestaurantBrand(restaurantBrand);

				response.setData(restaurantBrand);
			}

		} else {
			response.setStatus(HttpStatus.NOT_FOUND);
			response.setMessageError(HttpStatus.NOT_FOUND.name());
		}

		response.setData(new RestaurantBrandResponse(restaurantBrand));
		return new ResponseEntity<BaseResponse>(response, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/{id}/delete", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseResponse> deleteById(@PathVariable("id") int id) throws Exception {
		BaseResponse response = new BaseResponse();
		RestaurantBrand brand = restaurantBrandService.findById(id);

		if (brand != null) {
			restaurantBrandService.deleteById(brand);
			response.setData("Delete success");
		} else {
			response.setStatus(HttpStatus.NOT_FOUND);
			response.setMessageError(HttpStatus.NOT_FOUND.name());
		}
		return new ResponseEntity<BaseResponse>(response, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/q", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseResponse> getFilter(
			@RequestParam(value = "status", defaultValue = "-1", required = false) Integer status,
			@RequestParam(value = "restaurant_id", defaultValue = "-1", required = false) Integer restaurantId,
			@RequestParam(value = "keyword", defaultValue = "", required = false) String keyword) throws Exception {

		BaseResponse response = new BaseResponse();
		StoreProcedureListResult<RestaurantBrand> restaurants = restaurantBrandService
				.spGFilterRestaurantBrands(keyword, restaurantId, status);
		response.setData(new RestaurantBrandResponse().mapToList(restaurants.getResult()));
		return new ResponseEntity<BaseResponse>(response, HttpStatus.OK);
	}
}
