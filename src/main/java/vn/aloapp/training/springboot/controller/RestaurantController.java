package vn.aloapp.training.springboot.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.aloapp.training.springboot.entity.Area;
import vn.aloapp.training.springboot.entity.Branch;
import vn.aloapp.training.springboot.entity.Restaurant;
import vn.aloapp.training.springboot.entity.StoreProcedureListResult;
import vn.aloapp.training.springboot.entity.Table;
import vn.aloapp.training.springboot.request.CRUDListRestaurantRequest;
import vn.aloapp.training.springboot.request.CRUDRestaurantRequest;
import vn.aloapp.training.springboot.response.AreaResponse;
import vn.aloapp.training.springboot.response.BaseResponse;
import vn.aloapp.training.springboot.response.RestaurantResponse;
import vn.aloapp.training.springboot.response.TableResponse;
import vn.aloapp.training.springboot.service.AreaService;
import vn.aloapp.training.springboot.service.BranchService;
import vn.aloapp.training.springboot.service.RestaurantBrandService;
import vn.aloapp.training.springboot.service.RestaurantService;
import vn.aloapp.training.springboot.service.TableService;

@RestController
@RequestMapping("/api/v1/restaurants")
public class RestaurantController extends BaseController {

	@Autowired
	private RestaurantService restaurantService;

	@Autowired
	private AreaService areaService;

	@Autowired
	private TableService tableService;
	
	@Autowired
	private BranchService branchService;
	
	@Autowired
	private RestaurantBrandService  restaurantBrandService;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PostMapping(value = "/create", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseResponse<Restaurant>> create(@Valid @RequestBody CRUDRestaurantRequest wrapper)
			throws Exception {
		BaseResponse response = new BaseResponse();

		response.setData(new RestaurantResponse(restaurantService.spUCreateRestaurant(wrapper.getName(),
				wrapper.getEmail(), wrapper.getPhone(), wrapper.getInfo(), wrapper.getAddress(), wrapper.getLogo())));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/{id}/update", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseResponse> update(@PathVariable("id") Integer id,
			@RequestBody CRUDRestaurantRequest wrapper) throws Exception {
		BaseResponse response = new BaseResponse();
		Restaurant restaurant = restaurantService.findById(id);

		if (restaurant != null) {

			restaurant.setName(wrapper.getName());
			restaurant.setEmail(wrapper.getEmail());
			restaurant.setPhone(wrapper.getPhone());
			restaurant.setLogo(wrapper.getLogo());
			restaurant.setInfo(wrapper.getInfo());
			restaurant.setAddress(wrapper.getAddress());

			restaurantService.spUpdateRestaurant(restaurant);

			response.setData(new RestaurantResponse(restaurant));
		} else {
			response.setStatus(HttpStatus.BAD_REQUEST);
			response.setMessageError(HttpStatus.BAD_REQUEST.name());
		}

		return new ResponseEntity<BaseResponse>(response, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseResponse> getList() throws Exception {
		BaseResponse response = new BaseResponse();
		StoreProcedureListResult<Restaurant> restaurants = restaurantService.spGListRestaurants();
		response.setData(new RestaurantResponse().mapToList(restaurants.getResult()));
		return new ResponseEntity<BaseResponse>(response, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseResponse> getById(@PathVariable("id") Integer id) throws Exception {
		BaseResponse response = new BaseResponse();
		Restaurant restaurant = restaurantService.findOneById(id);

		if (restaurant != null) {
			response.setData(new RestaurantResponse(restaurant));
		} else {
			response.setStatus(HttpStatus.BAD_REQUEST);
			response.setMessageError(HttpStatus.BAD_REQUEST.name());
		}

		return new ResponseEntity<BaseResponse>(response, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/{id}/delete", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseResponse> delete(@PathVariable("id") Integer id,
			@RequestBody CRUDRestaurantRequest wrapper) throws Exception {
		BaseResponse response = new BaseResponse();
		Restaurant restaurant = restaurantService.findOneById(id);

		if (restaurant != null) {
			restaurantService.deleteById(restaurant);
			response.setData("Delete success");
		} else {
			response.setStatus(HttpStatus.BAD_REQUEST);
			response.setMessageError(HttpStatus.BAD_REQUEST.name());
		}

		return new ResponseEntity<BaseResponse>(response, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/q", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseResponse> getFilter(
			@RequestParam(value = "status", defaultValue = "-1", required = false) Integer status,
			@RequestParam(value = "keyword", defaultValue = "", required = false) String keyword) throws Exception {

		BaseResponse response = new BaseResponse();
		StoreProcedureListResult<Restaurant> restaurants = restaurantService.spGFilterRestaurants(keyword, status);
		response.setData(new RestaurantResponse().mapToList(restaurants.getResult()));
		return new ResponseEntity<BaseResponse>(response, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PostMapping(value = "/create-list", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseResponse<Restaurant>> createList(@Valid @RequestBody CRUDListRestaurantRequest requests)
			throws Exception {
		BaseResponse response = new BaseResponse();
		String json = new ObjectMapper().writeValueAsString(requests.getListObject());
		response.setData(restaurantService.spUCreateRestaurants(json));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping(value = "/{id}/detail", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseResponse<Restaurant>> getDetail(@PathVariable("id") int id) throws Exception {
		BaseResponse response = new BaseResponse();

		List<Area> areas = areaService.spGAreasByBranchIds(null);
		List<Branch> branches = branchService.spGBranchByRestaurantBrandIds(null);
		List<Table> tables = tableService.spGTablesByAreaIds(null);

		List<AreaResponse> areasResponses = new AreaResponse().mapToList(areas);
		List<TableResponse> tableResponses = new TableResponse().mapToList(tables);

		areasResponses = areasResponses.stream().map(x -> {
			List<TableResponse> tableResponseData = tableResponses.stream().filter(y -> y.getAreaId() == x.getId())
					.collect(Collectors.toList());
			x.getList().setTotalRecord(tableResponseData.size());
			x.getList().setList(tableResponses);
			return x;
		}).collect(Collectors.toList());

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
