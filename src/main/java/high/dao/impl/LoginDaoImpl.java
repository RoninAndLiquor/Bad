package high.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSON;
import com.sun.tools.javac.util.Log;

import high.dao.LoginDao;
import high.entity.Login;
import high.mapper.LoginMapper;

@Repository
public class LoginDaoImpl implements LoginDao{

	private final Logger LOG = LoggerFactory.getLogger(LoginDao.class);
	
	@Autowired
	private LoginMapper loginMapper;
	
	@Override
	public void insert(Login login) {
		String result = null;
		Map<String,Object> paramMap = new HashMap<String,Object>();
		if(null!=login){
			if(login.getLoginJobnum()!=null&&!login.getLoginJobnum().equals("")){
				paramMap.put("loginJobnum", login.getLoginJobnum());
			}
			if(login.getLoginPass()!=null&&!login.getLoginPass().equals("")){
				paramMap.put("loginPass", login.getLoginPass());
			}
			loginMapper.insert(paramMap);
		}
	}

	@Override
	public Login queryByNameAndPwd(String userName, String password) {
		if(null!=userName && !userName.equals("") && null!=password && !password.equals("")){
			Map<String,Object> paramMap = new HashMap<String,Object>();
			paramMap.put("userName", userName);
			paramMap.put("password", password);
			Login login = loginMapper.queryByNameAndPwd(paramMap);
			LOG.info("*** 结果集{} *** : "+JSON.toJSONString(login));
			return login;
		}
		return null;
	}

}
