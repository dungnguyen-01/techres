package vn.aloapp.training.springboot.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.codehaus.jackson.map.ObjectMapper;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import vn.aloapp.training.common.enums.StoreProcedureStatusCodeEnum;
import vn.aloapp.training.common.exception.TechresHttpException;
import vn.aloapp.training.springboot.entity.Restaurant;
import vn.aloapp.training.springboot.entity.StoreProcedureListResult;
import vn.aloapp.training.springboot.request.CRUDRestaurantRequest;

@Repository("restaurantDao")
@SuppressWarnings("unchecked")
public class RestaurantDaoImpl extends AbstractDao<Integer, Restaurant> implements RestaurantDao {
	
	@Autowired
	EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public Restaurant spUCreateRestaurant(String name, String email, String phone, String info, String address,
			String logo) throws Exception {
		StoredProcedureQuery query = this.getSession()
				.createStoredProcedureQuery("sp_u_create_restaurant", Restaurant.class)
				.registerStoredProcedureParameter("_name", String.class, ParameterMode.IN)
				.registerStoredProcedureParameter("_email", String.class, ParameterMode.IN)
				.registerStoredProcedureParameter("_phone", String.class, ParameterMode.IN)
				.registerStoredProcedureParameter("_info", String.class, ParameterMode.IN)
				.registerStoredProcedureParameter("_address", String.class, ParameterMode.IN)
				.registerStoredProcedureParameter("_logo", String.class, ParameterMode.IN)

				.registerStoredProcedureParameter("status_code", Integer.class, ParameterMode.OUT)
				.registerStoredProcedureParameter("message_error", String.class, ParameterMode.OUT);

		query.setParameter("_name", name);
		query.setParameter("_email", email);
		query.setParameter("_phone", phone);
		query.setParameter("_info", info);
		query.setParameter("_address", address);
		query.setParameter("_logo", logo);

		int statusCode = (int) query.getOutputParameterValue("status_code");
		String messageError = query.getOutputParameterValue("message_error").toString();

		switch (StoreProcedureStatusCodeEnum.valueOf(statusCode)) {
		case SUCCESS:
			return (Restaurant) query.getResultList().stream().findFirst().orElse(null);
		case INPUT_INVALID:
			throw new TechresHttpException(HttpStatus.BAD_REQUEST, messageError);
		default:
			throw new Exception(messageError);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Restaurant> spGListRestaurants() throws Exception {
		// TODO Auto-generated method stub
		StoredProcedureQuery query = this.getSession()
				.createStoredProcedureQuery("sp_g_list_restaurant", Restaurant.class)

				.registerStoredProcedureParameter("status_code", Integer.class, ParameterMode.OUT)
				.registerStoredProcedureParameter("message_error", String.class, ParameterMode.OUT);

		int statusCode = (int) query.getOutputParameterValue("status_code");
		String messageError = query.getOutputParameterValue("message_error").toString();

		switch (StoreProcedureStatusCodeEnum.valueOf(statusCode)) {
		case SUCCESS:
			return query.getResultList();
		case INPUT_INVALID:
			throw new TechresHttpException(HttpStatus.BAD_REQUEST, messageError);
		default:
			throw new Exception(messageError);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Restaurant findById(Integer id) throws Exception {
		return (Restaurant) this.getSession().createCriteria(Restaurant.class).add(Restrictions.eq("id", id))
				.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Restaurant spUpdateRestaurant(Restaurant restaurant) throws Exception {
		StoredProcedureQuery query = this.getSession()
				.createStoredProcedureQuery("sp_u_update_restaurant", Restaurant.class)
				.registerStoredProcedureParameter("_id", Integer.class, ParameterMode.IN)
				.registerStoredProcedureParameter("_name", String.class, ParameterMode.IN)
				.registerStoredProcedureParameter("_email", String.class, ParameterMode.IN)
				.registerStoredProcedureParameter("_phone", String.class, ParameterMode.IN)
				.registerStoredProcedureParameter("_info", String.class, ParameterMode.IN)
				.registerStoredProcedureParameter("_address", String.class, ParameterMode.IN)
				.registerStoredProcedureParameter("_logo", String.class, ParameterMode.IN)

				.registerStoredProcedureParameter("status_code", Integer.class, ParameterMode.OUT)
				.registerStoredProcedureParameter("message_error", String.class, ParameterMode.OUT);

		query.setParameter("_id", restaurant.getId());
		query.setParameter("_name", restaurant.getName());
		query.setParameter("_email", restaurant.getEmail());
		query.setParameter("_phone", restaurant.getPhone());
		query.setParameter("_info", restaurant.getInfo());
		query.setParameter("_address", restaurant.getAddress());
		query.setParameter("_logo", restaurant.getLogo());

		int statusCode = (int) query.getOutputParameterValue("status_code");
		String messageError = query.getOutputParameterValue("message_error").toString();

		switch (StoreProcedureStatusCodeEnum.valueOf(statusCode)) {
		case SUCCESS:
			return (Restaurant) query.getResultList().stream().findFirst().orElse(null);
		case INPUT_INVALID:
			throw new TechresHttpException(HttpStatus.BAD_REQUEST, messageError);
		default:
			throw new Exception(messageError);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void deleteById(Restaurant restaurant) throws Exception {
		this.getSession().delete(restaurant);
	}

	@SuppressWarnings("unchecked")
	@Override
	public StoreProcedureListResult<Restaurant> spGFilterRestaurants(String keyword, Integer status) throws Exception {
		// TODO Auto-generated method stub
		StoredProcedureQuery query = this.getSession()
				.createStoredProcedureQuery("sp_g_filter_restaurant", Restaurant.class)
				.registerStoredProcedureParameter("_keyword", String.class, ParameterMode.IN)
				.registerStoredProcedureParameter("_status", Integer.class, ParameterMode.IN)
				.registerStoredProcedureParameter("status_code", Integer.class, ParameterMode.OUT)
				.registerStoredProcedureParameter("message_error", String.class, ParameterMode.OUT);

		query.setParameter("_keyword", keyword);
		query.setParameter("_status", status);

		int statusCode = (int) query.getOutputParameterValue("status_code");
		String messageError = query.getOutputParameterValue("message_error").toString();

		switch (StoreProcedureStatusCodeEnum.valueOf(statusCode)) {
		case SUCCESS:
			return new StoreProcedureListResult<Restaurant>(statusCode, messageError, query.getResultList());
		case INPUT_INVALID:
			throw new TechresHttpException(HttpStatus.BAD_REQUEST, messageError);
		default:
			throw new Exception(messageError);
		}
	}

	@Override
	public Restaurant findOneById(Integer id) throws Exception {
		CriteriaQuery<Restaurant> criteriaQuery = this.getBuilder().createQuery(Restaurant.class);
		Root<Restaurant> root = criteriaQuery.from(Restaurant.class);
		List<Predicate> predicates = new ArrayList<Predicate>();
		predicates.add(this.getBuilder().equal(root.get("id"), id));
		criteriaQuery.select(root).where(predicates.toArray(new Predicate[] {}));
		List<Restaurant> results = this.getSession().createQuery(criteriaQuery).getResultList();
		return results.stream().findFirst().orElse(null);
	}

	@Override
	public List<Restaurant> spUCreateRestaurants(String json) throws Exception {
	    StoredProcedureQuery query = this.getSession()
	    		.createStoredProcedureQuery("sp_u_test", Restaurant.class)

	    .registerStoredProcedureParameter("restaurants", String.class, ParameterMode.IN)
	    .registerStoredProcedureParameter("status_code", Integer.class, ParameterMode.OUT)
	    .registerStoredProcedureParameter("message_error", String.class, ParameterMode.OUT);
	    	    
	    query.setParameter("restaurants", json);
	   
	    int statusCode = (int) query.getOutputParameterValue("status_code");
	    String messageError = (String) query.getOutputParameterValue("message_error");

	    switch (StoreProcedureStatusCodeEnum.valueOf(statusCode)) {
	        case SUCCESS:
	            return query.getResultList();
	        case INPUT_INVALID:
	            throw new TechresHttpException(HttpStatus.BAD_REQUEST, messageError);
	        default:
	            throw new Exception(messageError);
	    }
	}

}
