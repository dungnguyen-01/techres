package vn.aloapp.training.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.aloapp.training.springboot.dao.RestaurantBrandDao;
import vn.aloapp.training.springboot.entity.RestaurantBrand;
import vn.aloapp.training.springboot.entity.StoreProcedureListResult;

@Service
@Transactional
public class RestaurantBrandServiceImpl implements RestaurantBrandService{
	@Autowired
	RestaurantBrandDao brandDao;

	@Override
	public RestaurantBrand spUCreateRestaurantBrand(String name, String banner, String logoUrl, String description,
			int restaurantId) throws Exception {
		// TODO Auto-generated method stub
		return brandDao.spUCreateRestaurantBrand(name, banner, logoUrl, description, restaurantId);
	}

	@Override
	public StoreProcedureListResult<RestaurantBrand> spGListRestaurantBrands() throws Exception {
		// TODO Auto-generated method stub
		return brandDao.spGListRestaurantBrands();
	}

	@Override
	public RestaurantBrand findById(Integer id) throws Exception{
		return brandDao.findById(id);
	}

	@Override
	public RestaurantBrand spUpdateRestaurantBrand(RestaurantBrand restaurantBrand) throws Exception {
		// TODO Auto-generated method stub
		return brandDao.spUpdateRestaurantBrand(restaurantBrand);
	}

	@Override
	public void deleteById(RestaurantBrand brand) throws Exception {
		brandDao.deleteById(brand);
	}

	@Override
	public StoreProcedureListResult<RestaurantBrand> spGFilterRestaurantBrands(String keyword, Integer restaurantId,
			Integer status) throws Exception {
		// TODO Auto-generated method stub
		return brandDao.spGFilterRestaurantBrands(keyword, restaurantId, status);
	}

	@Override
	public List<RestaurantBrand> spGRestaurantBrandByRestaurantIds(String restaurantIds)  throws Exception{
		// TODO Auto-generated method stub
		return brandDao.spGRestaurantBrandByRestaurantIds(restaurantIds);
	}

}
