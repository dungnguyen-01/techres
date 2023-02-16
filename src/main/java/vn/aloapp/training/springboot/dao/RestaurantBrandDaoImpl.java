package vn.aloapp.training.springboot.dao;

import java.util.List;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import org.hibernate.criterion.Restrictions;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import vn.aloapp.training.common.enums.StoreProcedureStatusCodeEnum;
import vn.aloapp.training.common.exception.TechresHttpException;
import vn.aloapp.training.springboot.entity.RestaurantBrand;
import vn.aloapp.training.springboot.entity.StoreProcedureListResult;
import vn.aloapp.training.springboot.entity.Table;

@Repository("restaurantBrandDao")
@SuppressWarnings("unchecked")
public class RestaurantBrandDaoImpl extends AbstractDao<Integer, RestaurantBrand> implements RestaurantBrandDao {

	@SuppressWarnings("unchecked")
	@Override
	public RestaurantBrand spUCreateRestaurantBrand(String name, String banner, String logoUrl, String description,
			int restaurantId) throws Exception {

		StoredProcedureQuery query = this.getSession()
				.createStoredProcedureQuery("sp_u_create_restaurant_brand", RestaurantBrand.class)
				.registerStoredProcedureParameter("_name", String.class, ParameterMode.IN)
				.registerStoredProcedureParameter("_banner", String.class, ParameterMode.IN)
				.registerStoredProcedureParameter("logoUrl", String.class, ParameterMode.IN)
				.registerStoredProcedureParameter("_description", String.class, ParameterMode.IN)
				.registerStoredProcedureParameter("restaurantId", Integer.class, ParameterMode.IN)

				.registerStoredProcedureParameter("status_code", Integer.class, ParameterMode.OUT)
				.registerStoredProcedureParameter("message_error", String.class, ParameterMode.OUT);

		query.setParameter("_name", name);
		query.setParameter("_banner", banner);
		query.setParameter("logoUrl", logoUrl);
		query.setParameter("_description", description);
		query.setParameter("restaurantId", restaurantId);

		int statusCode = (int) query.getOutputParameterValue("status_code");
		String messageError = query.getOutputParameterValue("message_error").toString();

		switch (StoreProcedureStatusCodeEnum.valueOf(statusCode)) {
		case SUCCESS:
			return (RestaurantBrand) query.getResultList().stream().findFirst().orElse(null);
		case INPUT_INVALID:
			throw new TechresHttpException(HttpStatus.BAD_REQUEST, messageError);
		default:
			throw new Exception(messageError);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public StoreProcedureListResult<RestaurantBrand> spGListRestaurantBrands() throws Exception {
		StoredProcedureQuery query = this.getSession()
				.createStoredProcedureQuery("sp_g_list_restaurants_brand", RestaurantBrand.class)
				.registerStoredProcedureParameter("status_code", Integer.class, ParameterMode.OUT)
				.registerStoredProcedureParameter("message_error", String.class, ParameterMode.OUT);
		int statusCode = (int) query.getOutputParameterValue("status_code");
		String messageError = query.getOutputParameterValue("message_error").toString();

		switch (StoreProcedureStatusCodeEnum.valueOf(statusCode)) {
		case SUCCESS:
			return new StoreProcedureListResult<RestaurantBrand>(statusCode, messageError, query.getResultList());
		case INPUT_INVALID:
			throw new TechresHttpException(HttpStatus.BAD_REQUEST, messageError);
		default:
			throw new Exception(messageError);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public RestaurantBrand findById(Integer id) throws Exception {
		return (RestaurantBrand) this.getSession().createCriteria(RestaurantBrand.class).add(Restrictions.eq("id", id))
				.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public RestaurantBrand spUpdateRestaurantBrand(RestaurantBrand restaurantBrand) throws Exception {
		StoredProcedureQuery query = this.getSession()
				.createStoredProcedureQuery("sp_u_update_restaurant_brand", RestaurantBrand.class)
				.registerStoredProcedureParameter("_id", Integer.class, ParameterMode.IN)
				.registerStoredProcedureParameter("_name", String.class, ParameterMode.IN)
				.registerStoredProcedureParameter("_banner", String.class, ParameterMode.IN)
				.registerStoredProcedureParameter("logoUrl", String.class, ParameterMode.IN)
				.registerStoredProcedureParameter("_description", String.class, ParameterMode.IN)
				.registerStoredProcedureParameter("restaurantId", Integer.class, ParameterMode.IN)

				.registerStoredProcedureParameter("status_code", Integer.class, ParameterMode.OUT)
				.registerStoredProcedureParameter("message_error", String.class, ParameterMode.OUT);

		query.setParameter("_id", restaurantBrand.getId());
		query.setParameter("_name", restaurantBrand.getName());
		query.setParameter("_banner", restaurantBrand.getBanner());
		query.setParameter("logoUrl", restaurantBrand.getLogoUrl());
		query.setParameter("_description", restaurantBrand.getDescription());
		query.setParameter("restaurantId", restaurantBrand.getRestaurantId());

		int statusCode = (int) query.getOutputParameterValue("status_code");
		String messageError = query.getOutputParameterValue("message_error").toString();

		switch (StoreProcedureStatusCodeEnum.valueOf(statusCode)) {
		case SUCCESS:
			return (RestaurantBrand) query.getResultList().stream().findFirst().orElse(null);
		case INPUT_INVALID:
			throw new TechresHttpException(HttpStatus.BAD_REQUEST, messageError);
		default:
			throw new Exception(messageError);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void deleteById(RestaurantBrand brand) throws Exception {
		this.getSession().delete(brand);

	}

	@Override
	public StoreProcedureListResult<RestaurantBrand> spGFilterRestaurantBrands(String keyword, Integer restaurantId,
			Integer status) throws Exception {

		StoredProcedureQuery query = this.getSession()
				.createStoredProcedureQuery("sp_g_filter_restaurants_brands", RestaurantBrand.class)
				.registerStoredProcedureParameter("_keyword", String.class, ParameterMode.IN)
				.registerStoredProcedureParameter("_restaurant_id", Integer.class, ParameterMode.IN)
				.registerStoredProcedureParameter("_status", Integer.class, ParameterMode.IN)
				.registerStoredProcedureParameter("status_code", Integer.class, ParameterMode.OUT)
				.registerStoredProcedureParameter("message_error", String.class, ParameterMode.OUT);

		query.setParameter("_keyword", keyword);
		query.setParameter("_restaurant_id", restaurantId);
		query.setParameter("_status", status);

		int statusCode = (int) query.getOutputParameterValue("status_code");
		String messageError = query.getOutputParameterValue("message_error").toString();

		switch (StoreProcedureStatusCodeEnum.valueOf(statusCode)) {
		case SUCCESS:
			return new StoreProcedureListResult<RestaurantBrand>(statusCode, messageError, query.getResultList());
		case INPUT_INVALID:
			throw new TechresHttpException(HttpStatus.BAD_REQUEST, messageError);
		default:
			throw new Exception(messageError);
		}
	}

	@Override
	public List<RestaurantBrand> spGRestaurantBrandByRestaurantIds(String restaurantIds) throws Exception {
		StoredProcedureQuery query = this.getSession()
				.createStoredProcedureQuery("sp_g_restaurant_brand_by_restaurant_ids", RestaurantBrand.class)

				.registerStoredProcedureParameter("restaurantIds", String.class, ParameterMode.IN)		
				.registerStoredProcedureParameter("status_code", Integer.class, ParameterMode.OUT)
				.registerStoredProcedureParameter("message_error", String.class, ParameterMode.OUT);

		query.setParameter("restaurantIds", restaurantIds);

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

}
