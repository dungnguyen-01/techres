package vn.aloapp.training.springboot.dao;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import org.hibernate.criterion.Restrictions;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import vn.aloapp.training.common.enums.StoreProcedureStatusCodeEnum;
import vn.aloapp.training.common.exception.TechresHttpException;
import vn.aloapp.training.springboot.entity.Branch;
import vn.aloapp.training.springboot.entity.StoreProcedureListResult;

@Repository("branchDao")
@SuppressWarnings("unchecked")
public class BranchDaoImpl extends AbstractDao<Integer, Branch> implements BranchDao {

	@Override
	public Branch spUCreateBranch(String name, int restaurantId, int restaurantBrandId, String streetName,
			String phoneNumber, String addressFullText, int status) throws Exception {
		StoredProcedureQuery query = this.getSession().createStoredProcedureQuery("sp_u_create_branch", Branch.class)
				.registerStoredProcedureParameter("_name", String.class, ParameterMode.IN)
				.registerStoredProcedureParameter("phoneNumber", String.class, ParameterMode.IN)
				.registerStoredProcedureParameter("streetName", String.class, ParameterMode.IN)
				.registerStoredProcedureParameter("addressFullText", String.class, ParameterMode.IN)
				.registerStoredProcedureParameter("restaurantId", Integer.class, ParameterMode.IN)
				.registerStoredProcedureParameter("restaurantBrandId", Integer.class, ParameterMode.IN)
				.registerStoredProcedureParameter("_status", Integer.class, ParameterMode.IN)

				.registerStoredProcedureParameter("status_code", Integer.class, ParameterMode.OUT)
				.registerStoredProcedureParameter("message_error", String.class, ParameterMode.OUT);

		query.setParameter("_name", name);
		query.setParameter("phoneNumber", phoneNumber);
		query.setParameter("streetName", streetName);
		query.setParameter("addressFullText", addressFullText);
		query.setParameter("restaurantId", restaurantId);
		query.setParameter("restaurantBrandId", restaurantBrandId);
		query.setParameter("_status", status);

		int statusCode = (int) query.getOutputParameterValue("status_code");
		String messageError = query.getOutputParameterValue("message_error").toString();

		switch (StoreProcedureStatusCodeEnum.valueOf(statusCode)) {
		case SUCCESS:
			return (Branch) query.getResultList().stream().findFirst().orElse(null);
		case INPUT_INVALID:
			throw new TechresHttpException(HttpStatus.BAD_REQUEST, messageError);
		default:
			throw new Exception(messageError);
		}
	}

	@Override
	public Branch findById(Integer id) throws Exception {

		return (Branch) this.getSession().createCriteria(Branch.class).add(Restrictions.eq("id", id)).uniqueResult();
	}

	@Override
	public Branch spUpdateBranch(Branch branch) throws Exception {
		StoredProcedureQuery query = this.getSession().createStoredProcedureQuery("sp_u_update_branch", Branch.class)
				.registerStoredProcedureParameter("_id", Integer.class, ParameterMode.IN)
				.registerStoredProcedureParameter("_name", String.class, ParameterMode.IN)
				.registerStoredProcedureParameter("phoneNumber", String.class, ParameterMode.IN)
				.registerStoredProcedureParameter("streetName", String.class, ParameterMode.IN)
				.registerStoredProcedureParameter("addressFullText", String.class, ParameterMode.IN)
				.registerStoredProcedureParameter("restaurantId", Integer.class, ParameterMode.IN)
				.registerStoredProcedureParameter("restaurantBrandId", Integer.class, ParameterMode.IN)
				.registerStoredProcedureParameter("_status", Integer.class, ParameterMode.IN)

				.registerStoredProcedureParameter("status_code", Integer.class, ParameterMode.OUT)
				.registerStoredProcedureParameter("message_error", String.class, ParameterMode.OUT);

		query.setParameter("_id", branch.getId());
		query.setParameter("_name", branch.getName());
		query.setParameter("phoneNumber", branch.getPhoneNumber());
		query.setParameter("streetName", branch.getStreetName());
		query.setParameter("addressFullText", branch.getAddressFullText());
		query.setParameter("restaurantId", branch.getRestaurantId());
		query.setParameter("restaurantBrandId", branch.getRestaurantBrandId());
		query.setParameter("_status", branch.getStatus());

		int statusCode = (int) query.getOutputParameterValue("status_code");
		String messageError = query.getOutputParameterValue("message_error").toString();

		switch (StoreProcedureStatusCodeEnum.valueOf(statusCode)) {
		case SUCCESS:
			return (Branch) query.getResultList().stream().findFirst().orElse(null);
		case INPUT_INVALID:
			throw new TechresHttpException(HttpStatus.BAD_REQUEST, messageError);
		default:
			throw new Exception(messageError);
		}

	}

	@Override
	public StoreProcedureListResult<Branch> spGListBranches() throws Exception {
		// TODO Auto-generated method stub
		StoredProcedureQuery query = this.getSession().createStoredProcedureQuery("sp_g_list_branch", Branch.class)

				.registerStoredProcedureParameter("status_code", Integer.class, ParameterMode.OUT)
				.registerStoredProcedureParameter("message_error", String.class, ParameterMode.OUT);

		int statusCode = (int) query.getOutputParameterValue("status_code");
		String messageError = query.getOutputParameterValue("message_error").toString();

		switch (StoreProcedureStatusCodeEnum.valueOf(statusCode)) {
		case SUCCESS:
			return new StoreProcedureListResult<Branch>(statusCode, messageError, query.getResultList());
		case INPUT_INVALID:
			throw new TechresHttpException(HttpStatus.BAD_REQUEST, messageError);
		default:
			throw new Exception(messageError);
		}
	}

	@Override
	public void deleteById(Branch branch) throws Exception {
		this.getSession().delete(branch);

	}

	@Override
	public StoreProcedureListResult<Branch> spGFilterBranches(String keyword, Integer status, Integer restaurantBrandId,
			Integer restaurantId) throws Exception {
		// TODO Auto-generated method stub
		StoredProcedureQuery query = this.getSession().createStoredProcedureQuery("sp_g_filter_branches", Branch.class)
				.registerStoredProcedureParameter("_keyword", String.class, ParameterMode.IN)
				.registerStoredProcedureParameter("_status", Integer.class, ParameterMode.IN)
				.registerStoredProcedureParameter("restaurantId", Integer.class, ParameterMode.IN)
				.registerStoredProcedureParameter("restaurantBrandId", Integer.class, ParameterMode.IN)
				.registerStoredProcedureParameter("status_code", Integer.class, ParameterMode.OUT)
				.registerStoredProcedureParameter("message_error", String.class, ParameterMode.OUT);

		query.setParameter("_keyword", keyword);
		query.setParameter("_status", status);
		query.setParameter("restaurantId", restaurantId);
		query.setParameter("restaurantBrandId", restaurantBrandId);

		int statusCode = (int) query.getOutputParameterValue("status_code");
		String messageError = query.getOutputParameterValue("message_error").toString();

		switch (StoreProcedureStatusCodeEnum.valueOf(statusCode)) {
		case SUCCESS:
			return new StoreProcedureListResult<Branch>(statusCode, messageError, query.getResultList());
		case INPUT_INVALID:
			throw new TechresHttpException(HttpStatus.BAD_REQUEST, messageError);
		default:
			throw new Exception(messageError);
		}
	}

}
