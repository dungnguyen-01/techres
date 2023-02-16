package vn.aloapp.training.springboot.service;

import java.util.List;

import vn.aloapp.training.springboot.entity.Restaurant;
import vn.aloapp.training.springboot.entity.StoreProcedureListResult;
import vn.aloapp.training.springboot.request.CRUDRestaurantRequest;

public interface RestaurantService {

	public Restaurant spUCreateRestaurant(String name, String email, String phone, String info, String address,
			String logo) throws Exception;

	public List<Restaurant> spGListRestaurants() throws Exception;

	public Restaurant findById(Integer id) throws Exception;

	public Restaurant spUpdateRestaurant(Restaurant restaurant) throws Exception;

	public void deleteById(Restaurant restaurant) throws Exception;

	public StoreProcedureListResult<Restaurant> spGFilterRestaurants(String keyword, Integer status) throws Exception;

	public Restaurant findOneById(Integer id) throws Exception;

	public List<Restaurant> spUCreateRestaurants(String json) throws Exception;

}
