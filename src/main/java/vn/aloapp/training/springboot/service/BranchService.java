package vn.aloapp.training.springboot.service;

import java.util.List;

import vn.aloapp.training.springboot.entity.Branch;
import vn.aloapp.training.springboot.entity.StoreProcedureListResult;

public interface BranchService {

	Branch spUCreateBranch(String name, int restaurantId, int restaurantBrandId, String streetName, String phoneNumber,
			String addressFullText, int status) throws Exception;

	Branch findById(Integer id) throws Exception;

	Branch spUpdateBranch(Branch branch) throws Exception;

	StoreProcedureListResult<Branch> spGListBranches() throws Exception;

	void deleteById(Branch branch) throws Exception;

	StoreProcedureListResult<Branch> spGFilterBranches(String keyword, Integer restaurantId, Integer restaurantBrandId,
			Integer status) throws Exception;

	List<Branch> spGBranchByRestaurantBrandIds(String  restaurantBrandIds);

}
