package vn.aloapp.training.springboot.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.xml.ws.soap.AddressingFeature.Responses;

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
import vn.aloapp.training.springboot.entity.RestaurantBrand;
import vn.aloapp.training.springboot.entity.StoreProcedureListResult;
import vn.aloapp.training.springboot.entity.Table;
import vn.aloapp.training.springboot.request.CRUDListRestaurantRequest;
import vn.aloapp.training.springboot.request.CRUDRestaurantRequest;
import vn.aloapp.training.springboot.response.AreaResponse;
import vn.aloapp.training.springboot.response.BaseResponse;
import vn.aloapp.training.springboot.response.BranchResponse;
import vn.aloapp.training.springboot.response.ObjectList;
import vn.aloapp.training.springboot.response.RestaurantBrandResponse;
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
		List<Restaurant> restaurants = restaurantService.spGListRestaurants();
		response.setData(new RestaurantResponse().mapToList(restaurants));
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

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping(value = "/{id}/detail", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseResponse<Restaurant>> getDetail(@PathVariable("id") int id) throws Exception {
		BaseResponse response = new BaseResponse();
		
		List<Integer> listRestaurantBrands = new ArrayList<>();
		List<Integer> listBranches = new ArrayList<>();
		List<Integer> listAreas = new ArrayList<>();
		
		Restaurant restaurant = restaurantService.findOneById(id);
		
		List<RestaurantBrand> restaurantBrands = restaurantBrandService.spGRestaurantBrandByRestaurantIds(new ObjectMapper().writeValueAsString(restaurant.getId()));
		restaurantBrands.forEach(x -> listRestaurantBrands.add(x.getId()));

		List<Branch> branches = branchService.spGBranchByRestaurantBrandIds(new ObjectMapper().writeValueAsString(listRestaurantBrands));
		branches.forEach(x -> listBranches.add(x.getId()));
		
		List<Area> areas = areaService.spGAreasByBranchIds(new ObjectMapper().writeValueAsString(listBranches));
		areas.forEach(x -> listAreas.add(x.getId()));
		
		List<Table> tables = tableService.spGTablesByAreaIds(new ObjectMapper().writeValueAsString(listAreas));

		
		List<RestaurantBrandResponse> restaurantBrandResponses  = new RestaurantBrandResponse().mapToList(restaurantBrands);		
		List<BranchResponse> branchResponses  = new BranchResponse().mapToList(branches);
		List<AreaResponse> areaResponses = new AreaResponse().mapToList(areas);
		List<TableResponse> tableResponses = new TableResponse().mapToList(tables);			
		
		
		restaurantBrandResponses.stream().map(x -> {
			List<BranchResponse> branchResponseData = branchResponses.stream().filter(y -> y.getRestaurantBrandId() == x.getId())
					.collect(Collectors.toList());
			
			branchResponseData.stream().map(branch -> {
				List<AreaResponse> areaResponseData = areaResponses.stream().filter(y -> y.getBranchId() == branch.getId())
						.collect(Collectors.toList());
				
				areaResponses.stream().map(area -> {
					List<TableResponse> tableResponseData = tableResponses.stream().filter(y -> y.getAreaId() == area.getId())
							.collect(Collectors.toList());
					area.getList().setTotalRecord(tableResponseData.size());
					area.getList().setList(tableResponseData.size() > 0? tableResponseData : new ArrayList<>());
					return area;
				}).collect(Collectors.toList());
				
				branch.getList().setTotalRecord(areaResponseData.size());
				branch.getList().setList(areaResponseData);
				
				return branch;
			}).collect(Collectors.toList());
			
			
			x.getList().setTotalRecord(branchResponseData.size());
			x.getList().setList(branchResponseData);
			return x;
		}).collect(Collectors.toList());
		
			
		RestaurantResponse retaurant = new RestaurantResponse(restaurant);
		retaurant.getList().setTotalRecord(restaurantBrandResponses.size());
		retaurant.getList().setList(restaurantBrandResponses);
		
		response.setData(retaurant);
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping(value = "/list-tree", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseResponse<Restaurant>> getListTree() throws Exception {
		BaseResponse response = new BaseResponse();
		
		List<Restaurant> restaurants = restaurantService.spGListRestaurants();
		
		List<Integer> listRestaurants = new ArrayList<>();
		List<Integer> listRestaurantBrands = new ArrayList<>();
		List<Integer> listBranches = new ArrayList<>();
		List<Integer> listAreas = new ArrayList<>();
		
		
		restaurants.forEach(x -> listRestaurants.add(x.getId()));
		
		
		List<RestaurantBrand> restaurantBrands = restaurantBrandService.spGRestaurantBrandByRestaurantIds(new ObjectMapper().writeValueAsString(listRestaurants));
		restaurantBrands.forEach(x -> listRestaurantBrands.add(x.getId()));

		List<Branch> branches = branchService.spGBranchByRestaurantBrandIds(new ObjectMapper().writeValueAsString(listRestaurantBrands));
		branches.forEach(x -> listBranches.add(x.getId()));
		
		List<Area> areas = areaService.spGAreasByBranchIds(new ObjectMapper().writeValueAsString(listBranches));
		areas.forEach(x -> listAreas.add(x.getId()));
		
		List<Table> tables = tableService.spGTablesByAreaIds(new ObjectMapper().writeValueAsString(listAreas));

		
		List<RestaurantResponse> restaurantResponses  = new RestaurantResponse().mapToList(restaurants);		
		List<RestaurantBrandResponse> restaurantBrandResponses  = new RestaurantBrandResponse().mapToList(restaurantBrands);		
		List<BranchResponse> branchResponses  = new BranchResponse().mapToList(branches);
		List<AreaResponse> areaResponses = new AreaResponse().mapToList(areas);
		List<TableResponse> tableResponses = new TableResponse().mapToList(tables);			
		
		
		restaurantBrandResponses.stream().map(x -> {
			List<BranchResponse> branchResponseData = branchResponses.stream().filter(y -> y.getRestaurantBrandId() == x.getId())
					.collect(Collectors.toList());
			
			branchResponseData.stream().map(branch -> {
				List<AreaResponse> areaResponseData = areaResponses.stream().filter(y -> y.getBranchId() == branch.getId())
						.collect(Collectors.toList());
				
				areaResponses.stream().map(area -> {
					List<TableResponse> tableResponseData = tableResponses.stream().filter(y -> y.getAreaId() == area.getId())
							.collect(Collectors.toList());
					area.getList().setTotalRecord(tableResponseData.size());
					area.getList().setList(tableResponseData.size() > 0? tableResponseData : new ArrayList());
					return area;
				}).collect(Collectors.toList());
				
				branch.getList().setTotalRecord(areaResponseData.size());
				branch.getList().setList(areaResponseData);
				
				return branch;
			}).collect(Collectors.toList());
			
			
			x.getList().setTotalRecord(branchResponseData.size());
			x.getList().setList(branchResponseData);
			return x;
		}).collect(Collectors.toList());
		
			
		restaurants.forEach(restaurant -> {
			RestaurantResponse res = new RestaurantResponse(restaurant);
			res.getList().setTotalRecord(restaurantBrandResponses.size());
			res.getList().setList(restaurantBrandResponses);
			restaurantResponses.add(res);
		});
		
		
		response.setData(restaurantResponses);
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}


}
