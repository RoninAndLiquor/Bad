package high.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import high.dao.LoginDao;
import high.entity.Login;
import high.service.LoginService;


@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LoginDao loginDao;
	
	@Transactional
	public void insert(Login login) {
		loginDao.insert(login);
	}

	@Override
	public Login queryByNameAndPwd(String userName, String password) {
		// TODO Auto-generated method stub
		return loginDao.queryByNameAndPwd(userName, password);
	}

}
