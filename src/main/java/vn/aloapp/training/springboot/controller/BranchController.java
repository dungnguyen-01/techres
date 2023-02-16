package vn.aloapp.training.springboot.controller;

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

import vn.aloapp.training.springboot.entity.Branch;
import vn.aloapp.training.springboot.entity.StoreProcedureListResult;
import vn.aloapp.training.springboot.request.CRUDBranchRequest;
import vn.aloapp.training.springboot.response.BaseResponse;
import vn.aloapp.training.springboot.response.BranchResponse;
import vn.aloapp.training.springboot.service.BranchService;
import vn.aloapp.training.springboot.service.RestaurantBrandService;
import vn.aloapp.training.springboot.service.RestaurantService;

@RestController
@RequestMapping("/api/v1/branches")
public class BranchController {

	@Autowired
	private BranchService branchService;

	@Autowired
	private RestaurantService restaurantService;

	@Autowired
	private RestaurantBrandService restaurantBrandService;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PostMapping(value = "/create", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseResponse> create(@Valid @RequestBody CRUDBranchRequest wrapper) throws Exception {
		BaseResponse response = new BaseResponse();
		if (restaurantService.findById(wrapper.getRestaurantId()) == null
				|| restaurantBrandService.findById(wrapper.getRestaurantBrandId()) == null) {

			response.setMessageError(HttpStatus.BAD_REQUEST.name());
			response.setStatus(HttpStatus.BAD_REQUEST);

		} else {
			response.setData(new BranchResponse(branchService.spUCreateBranch(wrapper.getName(),
					wrapper.getRestaurantId(), wrapper.getRestaurantBrandId(), wrapper.getStreetName(),
					wrapper.getPhoneNumber(), wrapper.getAddressFullText(), wrapper.getStatus())));
		}

		return new ResponseEntity<BaseResponse>(response, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/{id}/update", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseResponse> update(@PathVariable("id") Integer id, @RequestBody CRUDBranchRequest wrapper)
			throws Exception {
		BaseResponse response = new BaseResponse();
		Branch branch = branchService.findById(id);

		if (branch != null) {
			if (restaurantService.findById(wrapper.getRestaurantId()) == null
					|| restaurantBrandService.findById(wrapper.getRestaurantBrandId()) == null) {

				response.setMessageError(HttpStatus.BAD_REQUEST.name());
				response.setStatus(HttpStatus.BAD_REQUEST);

			} else {
				branch.setName(wrapper.getName());
				branch.setPhoneNumber(wrapper.getPhoneNumber());
				branch.setStreetName(wrapper.getStreetName());
				branch.setAddressFullText(wrapper.getAddressFullText());
				branch.setRestaurantId(wrapper.getRestaurantId());
				branch.setRestaurantBrandId(wrapper.getRestaurantBrandId());
				branch.setStatus(wrapper.getStatus());

				branchService.spUpdateBranch(branch);

				response.setData(new BranchResponse(branch));
			}

		} else {
			response.setStatus(HttpStatus.NOT_FOUND);
			response.setMessageError(HttpStatus.NOT_FOUND.name());
		}

		return new ResponseEntity<BaseResponse>(response, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseResponse> getList() throws Exception {
		BaseResponse response = new BaseResponse();
		StoreProcedureListResult<Branch> branchs = branchService.spGListBranches();
		response.setData(new BranchResponse().mapToList(branchs.getResult()));
		return new ResponseEntity<BaseResponse>(response, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseResponse> getById(@PathVariable("id") Integer id) throws Exception {
		BaseResponse response = new BaseResponse();
		Branch Branch = branchService.findById(id);

		if (Branch != null) {
			response.setData(new BranchResponse(Branch));
		} else {
			response.setStatus(HttpStatus.BAD_REQUEST);
			response.setMessageError(HttpStatus.BAD_REQUEST.name());
		}

		return new ResponseEntity<BaseResponse>(response, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/{id}/delete", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseResponse> delete(@PathVariable("id") Integer id, @RequestBody CRUDBranchRequest wrapper)
			throws Exception {
		BaseResponse response = new BaseResponse();
		Branch branch = branchService.findById(id);

		if (branch != null) {
			branchService.deleteById(branch);
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
			@RequestParam(value = "restaurant_id", defaultValue = "-1", required = false) Integer restaurantId,
			@RequestParam(value = "restaurant_brand_id", defaultValue = "-1", required = false) Integer restaurantBrandId,
			@RequestParam(value = "keyword", defaultValue = "", required = false) String keyword) throws Exception {

		BaseResponse response = new BaseResponse();
		StoreProcedureListResult<Branch> branchs = branchService.spGFilterBranches(keyword, restaurantId,
				restaurantBrandId, status);
		response.setData(new BranchResponse().mapToList(branchs.getResult()));
		return new ResponseEntity<BaseResponse>(response, HttpStatus.OK);
	}

}
