package vn.aloapp.training.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.aloapp.training.springboot.dao.TableDao;
import vn.aloapp.training.springboot.entity.StoreProcedureListResult;
import vn.aloapp.training.springboot.entity.Table;

@Service
@Transactional
public class TableServiceImpl implements TableService {

	@Autowired
	TableDao dao;

	@Override
	public Table spUCreateTable(String name, int restaurantId, int restaurantBrandId, int brandId, int areaId,
			String description, int totalSlot, int status) throws Exception {
		return dao.spUCreateTable(name, restaurantId, restaurantBrandId, brandId, areaId, totalSlot, description,
				status);
	}

	@Override
	public Table findById(Integer id) throws Exception {
		return dao.findById(id);
	}

	@Override
	public Table spUpdateTable(Table table) throws Exception {
		return dao.spUpdateTable(table);

	}

	@Override
	public void deleteById(Table table) throws Exception {
		dao.deleteById(table);

	}

	@Override
	public StoreProcedureListResult<Table> spGFilterTables(String keyword, Integer branchId, Integer areaId,
			Integer restaurantId, Integer restaurantBrandId, Integer status) throws Exception {
		return dao.spGFilterTables(keyword, branchId, areaId, restaurantId, restaurantBrandId, status);
	}

	@Override
	public StoreProcedureListResult<Table> spGListTables() throws Exception {
		return dao.spGListTable();
	}

	@Override
	public List<Table> spGTablesByAreaIds(String areaIds) throws Exception {
		return dao.spGTablesByAreaIds(areaIds);
	}

}
