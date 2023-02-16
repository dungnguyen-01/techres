/**
 * 
 */
package vn.aloapp.training.springboot.dao;

import java.util.List;

import vn.aloapp.training.springboot.entity.Area;
import vn.aloapp.training.springboot.entity.StoreProcedureListResult;

/**
 * @author Long Nguyen
 *
 */
public interface AreaDao {

	StoreProcedureListResult<Area> spGListAreas() throws Exception;

	Area create(Area entity);

	void update(Area entity) throws Exception;

	Area findById(int id) throws Exception;

	List<Area> findAll();

	public StoreProcedureListResult<Area> spGlAreas(String name) throws Exception;

	Area spUCreateAreas(String name, int restaurantId, int restaurantBrandId, int brandId, String description,
			int status) throws Exception;

	Area spUpdateArea(Area area) throws Exception;

	void deleteById(Area area) throws Exception;

	StoreProcedureListResult<Area> spGFilterAreas(String keyword, Integer branchId, Integer restaurantId,
			Integer restaurantBrandId, int status) throws Exception;

	public List<Area> spGAreasByBranchIds(String branchIds) throws Exception;
}
