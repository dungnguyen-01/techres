package vn.aloapp.training.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.aloapp.training.springboot.dao.RestaurantDao;
import vn.aloapp.training.springboot.entity.Restaurant;
import vn.aloapp.training.springboot.entity.RestaurantBrand;
import vn.aloapp.training.springboot.entity.StoreProcedureListResult;
import vn.aloapp.training.springboot.entity.StoreProcedureResult;
import vn.aloapp.training.springboot.request.CRUDRestaurantRequest;

@Service("restaurantService")
@Transactional
public class RestaurantServiceImpl implements RestaurantService {

	@Autowired
	RestaurantDao dao;

	@Override
	public Restaurant spUCreateRestaurant(String name, String email, String phone, String info, String address,
			String logo) throws Exception {
		return dao.spUCreateRestaurant(name, email, phone, info, address, logo);
	}

	@Override
	public StoreProcedureListResult<Restaurant> spGListRestaurants() throws Exception {
		return dao.spGListRestaurants();
	}

	@Override
	public Restaurant findById(Integer id) throws Exception {
		return dao.findById(id);
	}

	@Override
	public Restaurant spUpdateRestaurant(Restaurant restaurant) throws Exception {
		return dao.spUpdateRestaurant(restaurant);
	}

	@Override
	public void deleteById(Restaurant restaurant) throws Exception {
		dao.deleteById(restaurant);
	}

	@Override
	public StoreProcedureListResult<Restaurant> spGFilterRestaurants(String keyword, Integer status) throws Exception {
		return dao.spGFilterRestaurants(keyword, status);
	}

	@Override
	public Restaurant findOneById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return dao.findOneById(id);
	}

	@Override
	public List<Restaurant> spUCreateRestaurants(String json) throws Exception {
		 return dao.spUCreateRestaurants(json);
	
	}

}
