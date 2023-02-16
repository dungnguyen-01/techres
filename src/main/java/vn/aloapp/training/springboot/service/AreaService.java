package vn.aloapp.training.springboot.service;

import java.util.List;

import vn.aloapp.training.springboot.entity.Area;
import vn.aloapp.training.springboot.entity.StoreProcedureListResult;

public interface AreaService {

	Area create(Area entity);

	void update(Area entity) throws Exception;

	Area findById(int id) throws Exception;

	List<Area> findAll();

	public StoreProcedureListResult<Area> spGlAreas(String name) throws Exception;

	StoreProcedureListResult<Area> spGListAreas() throws Exception;

	Area spUCreateAreas(String name, int restaurantId, int restaurantBrandId, int brandId, String description,
			int status) throws Exception;

	Area spUpdateArea(Area area)throws Exception;

	void deleteById(Area area) throws Exception;

	StoreProcedureListResult<Area> spGFilterAreas(String keyword, Integer branchId, Integer restaurantId,
			Integer restaurantBrandId, Integer status) throws Exception;

	public List<Area> spGAreasByBranchIds(String branchIds) throws Exception;
	
}
