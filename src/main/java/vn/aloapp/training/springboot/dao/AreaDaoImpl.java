/**
 * 
 */
package vn.aloapp.training.springboot.dao;

import java.util.List;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.criterion.Restrictions;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import vn.aloapp.training.common.enums.StoreProcedureStatusCodeEnum;
import vn.aloapp.training.common.exception.TechresHttpException;
import vn.aloapp.training.springboot.entity.Area;
import vn.aloapp.training.springboot.entity.StoreProcedureListResult;

/**
 * @author Long Nguyen
 *
 */
@Repository("areaDao")
@SuppressWarnings("unchecked")
public class AreaDaoImpl extends AbstractDao<Integer, Area> implements AreaDao {

	@Override
	public Area findById(int id) throws Exception {
		return (Area) this.getSession().createCriteria(Area.class).add(Restrictions.eq("id", id)).uniqueResult();
	}

	@Override
	public List<Area> findAll() {
		// TODO Auto-generated method stub
		CriteriaQuery<Area> criteria = this.getBuilder().createQuery(Area.class);

		Root<Area> root = criteria.from(Area.class);
		criteria.select(root);
		return this.getSession().createQuery(criteria).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public StoreProcedureListResult<Area> spGlAreas(String name) throws Exception {

		return null;
	}

	@Override
	public Area create(Area entity) {
		// TODO Auto-generated method stub
		return null;
	}

	// viet lai crud

	@SuppressWarnings("unchecked")
	@Override
	public StoreProcedureListResult<Area> spGListAreas() throws Exception {
		StoredProcedureQuery query = this.getSession().createStoredProcedureQuery("sp_g_list_area", Area.class)
				.registerStoredProcedureParameter("status_code", Integer.class, ParameterMode.OUT)
				.registerStoredProcedureParameter("message_error", String.class, ParameterMode.OUT);

		int statusCode = (int) query.getOutputParameterValue("status_code");
		String messageError = query.getOutputParameterValue("message_error").toString();

		switch (StoreProcedureStatusCodeEnum.valueOf(statusCode)) {
		case SUCCESS:
			return new StoreProcedureListResult<Area>(statusCode, messageError, query.getResultList());
		case INPUT_INVALID:
			throw new TechresHttpException(HttpStatus.BAD_REQUEST, messageError);
		default:
			throw new Exception(messageError);
		}

	}

	@Override
	public Area spUCreateAreas(String name, int restaurantId, int restaurantBrandId, int brandId, String description,
			int status) throws Exception {

		StoredProcedureQuery query = this.getSession().createStoredProcedureQuery("sp_u_create_area", Area.class)
				.registerStoredProcedureParameter("_name", String.class, ParameterMode.IN)
				.registerStoredProcedureParameter("restaurantId", Integer.class, ParameterMode.IN)
				.registerStoredProcedureParameter("restaurantBrandId", Integer.class, ParameterMode.IN)
				.registerStoredProcedureParameter("branchId", Integer.class, ParameterMode.IN)
				.registerStoredProcedureParameter("_description", String.class, ParameterMode.IN)
				.registerStoredProcedureParameter("_status", Integer.class, ParameterMode.IN)

				.registerStoredProcedureParameter("status_code", Integer.class, ParameterMode.OUT)
				.registerStoredProcedureParameter("message_error", String.class, ParameterMode.OUT);

		query.setParameter("_name", name);
		query.setParameter("restaurantId", restaurantId);
		query.setParameter("restaurantBrandId", restaurantBrandId);
		query.setParameter("branchId", brandId);
		query.setParameter("_description", description);
		query.setParameter("_status", status);

		int statusCode = (int) query.getOutputParameterValue("status_code");
		String messageError = query.getOutputParameterValue("message_error").toString();

		switch (StoreProcedureStatusCodeEnum.valueOf(statusCode)) {
		case SUCCESS:
			return (Area) query.getResultList().stream().findFirst().orElse(null);
		case INPUT_INVALID:
			throw new TechresHttpException(HttpStatus.BAD_REQUEST, messageError);
		default:
			throw new Exception(messageError);
		}

	}

	@Override
	public void update(Area entity) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public Area spUpdateArea(Area entity) throws Exception {
		StoredProcedureQuery query = this.getSession().createStoredProcedureQuery("sp_u_update_area", Area.class)
				.registerStoredProcedureParameter("_id", Integer.class, ParameterMode.IN)
				.registerStoredProcedureParameter("_name", String.class, ParameterMode.IN)
				.registerStoredProcedureParameter("restaurantId", Integer.class, ParameterMode.IN)
				.registerStoredProcedureParameter("restaurantBrandId", Integer.class, ParameterMode.IN)
				.registerStoredProcedureParameter("branchId", Integer.class, ParameterMode.IN)
				.registerStoredProcedureParameter("_description", String.class, ParameterMode.IN)
				.registerStoredProcedureParameter("_status", Integer.class, ParameterMode.IN)

				.registerStoredProcedureParameter("status_code", Integer.class, ParameterMode.OUT)
				.registerStoredProcedureParameter("message_error", String.class, ParameterMode.OUT);

		query.setParameter("_id", entity.getId());
		query.setParameter("_name", entity.getName());
		query.setParameter("restaurantId", entity.getRestaurantId());
		query.setParameter("restaurantBrandId", entity.getRestaurantBrandId());
		query.setParameter("branchId", entity.getBranchId());
		query.setParameter("_description", entity.getDescription());
		query.setParameter("_status", entity.getStatus());

		int statusCode = (int) query.getOutputParameterValue("status_code");
		String messageError = query.getOutputParameterValue("message_error").toString();

		switch (StoreProcedureStatusCodeEnum.valueOf(statusCode)) {
		case SUCCESS:
			return (Area) query.getResultList().stream().findFirst().orElse(null);
		case INPUT_INVALID:
			throw new TechresHttpException(HttpStatus.BAD_REQUEST, messageError);
		default:
			throw new Exception(messageError);

		}

	}

	@Override
	public void deleteById(Area area) throws Exception {
		this.getSession().delete(area);
	}

	@Override
	public StoreProcedureListResult<Area> spGFilterAreas(String keyword, Integer branchId, Integer restaurantId,
			Integer restaurantBrandId, int status) throws Exception {
		StoredProcedureQuery query = this.getSession().createStoredProcedureQuery("sp_g_filter_areas", Area.class)
				.registerStoredProcedureParameter("_keyword", String.class, ParameterMode.IN)
				.registerStoredProcedureParameter("_status", Integer.class, ParameterMode.IN)
				.registerStoredProcedureParameter("branchId", Integer.class, ParameterMode.IN)
				.registerStoredProcedureParameter("restaurantId", Integer.class, ParameterMode.IN)
				.registerStoredProcedureParameter("restaurantBrandId", Integer.class, ParameterMode.IN)
				.registerStoredProcedureParameter("status_code", Integer.class, ParameterMode.OUT)
				.registerStoredProcedureParameter("message_error", String.class, ParameterMode.OUT);

		query.setParameter("_keyword", keyword);
		query.setParameter("_status", status);
		query.setParameter("branchId", branchId);
		query.setParameter("restaurantId", restaurantId);
		query.setParameter("restaurantBrandId", restaurantBrandId);

		int statusCode = (int) query.getOutputParameterValue("status_code");
		String messageError = query.getOutputParameterValue("message_error").toString();

		switch (StoreProcedureStatusCodeEnum.valueOf(statusCode)) {
		case SUCCESS:
			return new StoreProcedureListResult<Area>(statusCode, messageError, query.getResultList());
		case INPUT_INVALID:
			throw new TechresHttpException(HttpStatus.BAD_REQUEST, messageError);
		default:
			throw new Exception(messageError);
		}
	}

	@Override
	public List<Area> spGAreasByBranchIds(String branchIds) throws Exception {
		StoredProcedureQuery query = this.getSession()
				.createStoredProcedureQuery("sp_g_areas_by_branch_ids", Area.class)
				.registerStoredProcedureParameter("branchIds", String.class, ParameterMode.IN)
				.registerStoredProcedureParameter("status_code", Integer.class, ParameterMode.OUT)
				.registerStoredProcedureParameter("message_error", String.class, ParameterMode.OUT);

		query.setParameter("branchIds", branchIds);

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
