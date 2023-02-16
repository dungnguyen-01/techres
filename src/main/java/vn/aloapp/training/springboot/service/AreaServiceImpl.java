package vn.aloapp.training.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.aloapp.training.springboot.dao.AreaDao;
import vn.aloapp.training.springboot.entity.Area;
import vn.aloapp.training.springboot.entity.StoreProcedureListResult;

@Service
@Transactional
public class AreaServiceImpl implements AreaService {

	@Autowired
	AreaDao dao;

	@Override
	public Area findById(int id) throws Exception {
		return dao.findById(id);
	}

	@Override
	public List<Area> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public Area create(Area entity) {
		// TODO Auto-generated method stub
		return dao.create(entity);
	}

	@Override
	public void update(Area entity) throws Exception {
		// TODO Auto-generated method stub
		dao.update(entity);
	}

	@Override
	public StoreProcedureListResult<Area> spGlAreas(String name) throws Exception {
		// TODO Auto-generated method stub
		return dao.spGlAreas(name);
	}

	@Override
	public StoreProcedureListResult<Area> spGListAreas() throws Exception {
		// TODO Auto-generated method stub
		return dao.spGListAreas();
	}

	@Override
	public Area spUCreateAreas(String name, int restaurantId, int restaurantBrandId, int brandId, String description,
			int status) throws Exception {
		// TODO Auto-generated method stub
		return dao.spUCreateAreas(name, restaurantId, restaurantBrandId, brandId, description, status);
	}

	@Override
	public Area spUpdateArea(Area area) throws Exception {
		// TODO Auto-generated method stub
		return dao.spUpdateArea(area);
	}

	@Override
	public void deleteById(Area area) throws Exception {
		dao.deleteById(area);

	}

	@Override
	public StoreProcedureListResult<Area> spGFilterAreas(String keyword, Integer branchId, Integer restaurantId,
			Integer restaurantBrandId, Integer status) throws Exception {
		return dao.spGFilterAreas(keyword, branchId, restaurantId, restaurantBrandId, status);
	}

	@Override
	public List<Area> spGAreasByBranchIds(String branchIds) throws Exception {
		return dao.spGAreasByBranchIds(branchIds);
	}

}
