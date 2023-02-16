package vn.aloapp.training.springboot.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.aloapp.training.springboot.dao.BranchDao;
import vn.aloapp.training.springboot.entity.Branch;
import vn.aloapp.training.springboot.entity.StoreProcedureListResult;

@Service
@Transactional
public class BranchServiceImpl implements BranchService{
	
	@Autowired
	BranchDao dao;

	@Override
	public Branch spUCreateBranch(String name, int restaurantId, int restaurantBrandId, String streetName,
			String phoneNumber, String addressFullText, int status) throws Exception {
		// TODO Auto-generated method stub
		return dao.spUCreateBranch(name, restaurantId, restaurantBrandId, streetName, phoneNumber, addressFullText, status);
	}

	@Override
	public Branch findById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return dao.findById(id);
	}

	@Override
	public Branch spUpdateBranch(Branch branch) throws Exception {
		return dao.spUpdateBranch(branch);
	}

	@Override
	public StoreProcedureListResult<Branch> spGListBranches() throws Exception {
		return dao.spGListBranches();
	}

	@Override
	public void deleteById(Branch branch) throws Exception {
		dao.deleteById(branch);
		
	}

	@Override
	public StoreProcedureListResult<Branch> spGFilterBranches(String keyword, Integer restaurantId,
			Integer restaurantBrandId, Integer status) throws Exception {
		
		return dao.spGFilterBranches(keyword, status, restaurantBrandId, restaurantId);
	}

	@Override
	public List<Branch> spGBranchByRestaurantBrandIds(String restaurantBrandIds) throws Exception {
		// TODO Auto-generated method stub
		return dao.spGBranchByRestaurantBrandIds(restaurantBrandIds);
	}

	

}
