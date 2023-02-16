package vn.aloapp.training.springboot.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import vn.aloapp.training.springboot.entity.StoreProcedureListResult;
import vn.aloapp.training.springboot.entity.Table;
import vn.aloapp.training.springboot.request.CRUDTableRequest;
import vn.aloapp.training.springboot.response.BaseResponse;
import vn.aloapp.training.springboot.response.TableResponse;
import vn.aloapp.training.springboot.service.AreaService;
import vn.aloapp.training.springboot.service.BranchService;
import vn.aloapp.training.springboot.service.RestaurantBrandService;
import vn.aloapp.training.springboot.service.RestaurantService;
import vn.aloapp.training.springboot.service.TableService;

@RestController
@RequestMapping("/api/v1/tables")
public class TableController {

	@Autowired
	private TableService tableService;
	@Autowired
	private AreaService areaService;
	@Autowired
	private RestaurantService restaurantService;
	@Autowired
	private RestaurantBrandService restaurantBrandService;
	@Autowired
	private BranchService branchService;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PostMapping(value = "/create", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseResponse> create(@Valid @RequestBody CRUDTableRequest wrapper) throws Exception {
		BaseResponse response = new BaseResponse();

		if (areaService.findById(wrapper.getAreaId()) == null
				|| restaurantService.findById(wrapper.getRestaurantId()) == null
				|| restaurantBrandService.findById(wrapper.getRestaurantBrandId()) == null
				|| branchService.findById(wrapper.getBranchId()) == null) {
			response.setMessageError(HttpStatus.BAD_REQUEST.name());
			response.setStatus(HttpStatus.BAD_REQUEST);

		} else {
			response.setData(new TableResponse(tableService.spUCreateTable(wrapper.getName(), wrapper.getRestaurantId(),
					wrapper.getRestaurantBrandId(), wrapper.getBranchId(), wrapper.getAreaId(),
					wrapper.getDescription(), wrapper.getTotalSlot(), wrapper.getStatus())));
			response.setStatus(HttpStatus.OK);
		}
		return new ResponseEntity<BaseResponse>(response, HttpStatus.OK);

	}
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/{id}/update", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseResponse> update(@PathVariable("id") Integer id, @RequestBody CRUDTableRequest wrapper)
			throws Exception {
		BaseResponse response = new BaseResponse();
		Table table = tableService.findById(id);

		if (table != null) {
			if (areaService.findById(wrapper.getAreaId()) == null
					|| restaurantService.findById(wrapper.getRestaurantId()) == null
					|| restaurantBrandService.findById(wrapper.getRestaurantBrandId()) == null
					|| branchService.findById(wrapper.getBranchId()) == null) {
				response.setMessageError(HttpStatus.BAD_REQUEST.name());
				response.setStatus(HttpStatus.BAD_REQUEST);

			} else {

				table.setName(wrapper.getName());
				table.setAreaId(wrapper.getAreaId());
				table.setBranchId(wrapper.getBranchId());
				table.setRestaurantBrandId(wrapper.getRestaurantBrandId());
				table.setRestaurantId(wrapper.getRestaurantId());
				table.setDescription(wrapper.getDescription());
				table.setTotalSlot(wrapper.getTotalSlot());
				table.setStatus(wrapper.getStatus());

				tableService.spUpdateTable(table);

				response.setData(new TableResponse(table));
			}
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
		StoreProcedureListResult<Table> tables = tableService.spGListTables();
		response.setData(new TableResponse().mapToList(tables.getResult()));
		return new ResponseEntity<BaseResponse>(response, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseResponse> getById(@PathVariable("id") Integer id) throws Exception {
		BaseResponse response = new BaseResponse();
		Table table = tableService.findById(id);

		if (table != null) {
			response.setData(new TableResponse(table));
		} else {
			response.setStatus(HttpStatus.BAD_REQUEST);
			response.setMessageError(HttpStatus.BAD_REQUEST.name());
		}

		return new ResponseEntity<BaseResponse>(response, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/{id}/delete", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseResponse> delete(@PathVariable("id") Integer id, @RequestBody CRUDTableRequest wrapper)
			throws Exception {
		BaseResponse response = new BaseResponse();
		Table table = tableService.findById(id);

		if (table != null) {
			tableService.deleteById(table);
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
			@RequestParam(value = "branch_id", defaultValue = "-1", required = false) Integer branchId,
			@RequestParam(value = "area_id", defaultValue = "-1", required = false) Integer areaId,
			@RequestParam(value = "restaurant_id", defaultValue = "-1", required = false) Integer restaurantId,
			@RequestParam(value = "restaurant_brand_id", defaultValue = "-1", required = false) Integer restaurantBrandId,
			@RequestParam(value = "keyword", defaultValue = "", required = false) String keyword) throws Exception {

		BaseResponse response = new BaseResponse();
		StoreProcedureListResult<Table> tables = tableService.spGFilterTables(keyword, branchId, areaId, restaurantId,
				restaurantBrandId, status);
		response.setData(new TableResponse().mapToList(tables.getResult()));
		return new ResponseEntity<BaseResponse>(response, HttpStatus.OK);
	}

}
