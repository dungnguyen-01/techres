package vn.aloapp.training.springboot.service;

import vn.aloapp.training.springboot.entity.RestaurantBrand;
import vn.aloapp.training.springboot.entity.StoreProcedureListResult;

public interface RestaurantBrandService {

	RestaurantBrand spUCreateRestaurantBrand(String name, String banner, String logoUrl, String description,
			int restaurantId) throws Exception;

	StoreProcedureListResult<RestaurantBrand> spGListRestaurantBrands() throws Exception;

	RestaurantBrand findById(Integer id) throws Exception;

	RestaurantBrand spUpdateRestaurantBrand(RestaurantBrand restaurantBrand) throws Exception;

	void deleteById(RestaurantBrand brand) throws Exception;

	StoreProcedureListResult<RestaurantBrand> spGFilterRestaurantBrands(String keyword, Integer restaurantId,
			Integer status) throws Exception;

}
