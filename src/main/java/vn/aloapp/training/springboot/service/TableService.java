package vn.aloapp.training.springboot.service;

import java.util.List;

import vn.aloapp.training.springboot.entity.StoreProcedureListResult;
import vn.aloapp.training.springboot.entity.Table;

public interface TableService {

	Table spUCreateTable(String name, int restaurantId, int restaurantBrandId, int brandId, int areaId,
			String description, int totalSlot, int status) throws Exception;

	Table findById(Integer id) throws Exception;

	Table spUpdateTable(Table table) throws Exception;

	void deleteById(Table table) throws Exception;

	StoreProcedureListResult<Table> spGFilterTables(String keyword, Integer branchId, Integer areaId,
			Integer restaurantId, Integer restaurantBrandId, Integer status) throws Exception;

	StoreProcedureListResult<Table> spGListTables() throws Exception;

	public List<Table> spGTablesByAreaIds(String areaIds) throws Exception;

}
