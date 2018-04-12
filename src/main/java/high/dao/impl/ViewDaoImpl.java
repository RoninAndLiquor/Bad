package high.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import high.dao.ViewDao;
import high.mapper.ViewMapper;

@Repository
public class ViewDaoImpl implements ViewDao {

	@Autowired
	private ViewMapper view;
	
	@Override
	public List<Map<String, Object>> queryAll() {
		// TODO Auto-generated method stub
		return view.queryAll();
	}

}
