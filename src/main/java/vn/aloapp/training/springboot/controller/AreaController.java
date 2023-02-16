/**
 *
 */
package vn.aloapp.training.springboot.controller;

import javax.servlet.http.HttpServletRequest;
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

import vn.aloapp.training.springboot.entity.Area;
import vn.aloapp.training.springboot.entity.StoreProcedureListResult;
import vn.aloapp.training.springboot.request.CRUDAreaRequest;
import vn.aloapp.training.springboot.response.AreaResponse;
import vn.aloapp.training.springboot.response.BaseResponse;
import vn.aloapp.training.springboot.service.AreaService;
import vn.aloapp.training.springboot.service.BranchService;
import vn.aloapp.training.springboot.service.RestaurantBrandService;
import vn.aloapp.training.springboot.service.RestaurantService;

/**
 * @author kelvin
 *
 */
@RestController
@RequestMapping("/api/v1/areas")
public class AreaController extends BaseController {

	@Autowired
	private AreaService areaService;

	@Autowired
	private RestaurantService restaurantService;

	@Autowired
	private RestaurantBrandService brandService;

	@Autowired
	private BranchService branchService;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseResponse> getList() throws Exception {
		BaseResponse response = new BaseResponse();
		StoreProcedureListResult<Area> restaurants = areaService.spGListAreas();
		response.setData(new AreaResponse().mapToList(restaurants.getResult()));
		return new ResponseEntity<BaseResponse>(response, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/create", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseResponse> create(@Valid @RequestBody CRUDAreaRequest wrapper) throws Exception {
		BaseResponse response = new BaseResponse();

		if (restaurantService.findById(wrapper.getRestaurantId()) == null
				|| brandService.findById(wrapper.getRestaurantBrandId()) == null
				|| branchService.findById(wrapper.getBranchId()) == null) {
			response.setMessageError(HttpStatus.BAD_REQUEST.name());
			response.setStatus(HttpStatus.BAD_REQUEST);

		} else {
			response.setData(new AreaResponse(areaService.spUCreateAreas(wrapper.getName(), wrapper.getRestaurantId(),
					wrapper.getRestaurantBrandId(), wrapper.getBranchId(), wrapper.getDescription(),
					wrapper.getStatus())));

		}
		return new ResponseEntity<BaseResponse>(response, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/{id}/update", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseResponse> update(HttpServletRequest request, @PathVariable("id") int id,
			@Valid @RequestBody CRUDAreaRequest wrapper) throws Exception {
		BaseResponse response = new BaseResponse();

		Area area = areaService.findById(id);
		if (area == null) {
			response.setStatus(HttpStatus.BAD_REQUEST);
			response.setMessage(HttpStatus.BAD_REQUEST);
		} else {
			if (restaurantService.findById(wrapper.getRestaurantId()) == null
					|| brandService.findById(wrapper.getRestaurantBrandId()) == null
					|| branchService.findById(wrapper.getBranchId()) == null) {
				response.setMessageError(HttpStatus.NOT_FOUND.name());
				response.setStatus(HttpStatus.NOT_FOUND);

			} else {

				area.setName(wrapper.getName());
				area.setBranchId(wrapper.getBranchId());
				area.setRestaurantId(wrapper.getRestaurantId());
				area.setRestaurantBrandId(wrapper.getRestaurantBrandId());
				area.setStatus(wrapper.getStatus());
				area.setDescription(wrapper.getDescription());

				Area areaUpdate = areaService.spUpdateArea(area);
				response.setData(new AreaResponse(areaUpdate));
			}

		}

		return new ResponseEntity<BaseResponse>(response, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseResponse> getById(@PathVariable("id") Integer id) throws Exception {
		BaseResponse response = new BaseResponse();
		Area area = areaService.findById(id);

		if (area != null) {
			response.setData(new AreaResponse(area));
		} else {
			response.setStatus(HttpStatus.BAD_REQUEST);
			response.setMessageError(HttpStatus.BAD_REQUEST.name());
		}

		return new ResponseEntity<BaseResponse>(response, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/{id}/delete", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseResponse> delete(@PathVariable("id") Integer id) throws Exception {
		BaseResponse response = new BaseResponse();
		Area area = areaService.findById(id);

		if (area != null) {
			areaService.deleteById(area);
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
			@RequestParam(value = "branch_id", defaultValue = "-1", required = false) Integer branchId,
			@RequestParam(value = "restaurant_id", defaultValue = "-1", required = false) Integer restaurantId,
			@RequestParam(value = "restaurant_brand_id", defaultValue = "-1", required = false) Integer restaurantBrandId,
			@RequestParam(value = "keyword", defaultValue = "", required = false) String keyword) throws Exception {

		BaseResponse response = new BaseResponse();
		StoreProcedureListResult<Area> areas = areaService.spGFilterAreas(keyword, branchId, restaurantId,
				restaurantBrandId, status);
		response.setData(new AreaResponse().mapToList(areas.getResult()));
		return new ResponseEntity<BaseResponse>(response, HttpStatus.OK);
	}

}
