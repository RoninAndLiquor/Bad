package high.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import high.dao.ViewDao;
import high.service.ViewService;

@Service
@Scope("prototype")
public class ViewServiceImpl implements ViewService{

	private final static Logger LOG = LoggerFactory.getLogger(ViewServiceImpl.class);
	
	@Autowired
	private ViewDao view;
	
	@Override
	public List<Map<String, Object>> queryAll() {
		// TODO Auto-generated method stub
		List<Map<String, Object>> queryAll = view.queryAll();
		LOG.info("*** 结果集{} ***"+JSON.toJSONString(queryAll));
		return queryAll;
	}

}
