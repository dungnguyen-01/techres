package vn.aloapp.training.springboot.dao;

import java.util.List;

import vn.aloapp.training.springboot.entity.StoreProcedureListResult;
import vn.aloapp.training.springboot.entity.Table;

public interface TableDao {

	Table spUCreateTable(String name, int restaurantId, int restaurantBrandId, int brandId, int areaId, int totalSlot,
			String description, int status) throws Exception;

	Table findById(Integer id) throws Exception;

	Table spUpdateTable(Table table) throws Exception;

	void deleteById(Table table) throws Exception;

	StoreProcedureListResult<Table> spGFilterTables(String keyword, Integer branchId, Integer areaId,
			Integer restaurantId, Integer restaurantBrandId, Integer status) throws Exception;

	StoreProcedureListResult<Table> spGListTable() throws Exception;

	public List<Table> spGTablesByAreaIds(String areaIds) throws Exception;

}
