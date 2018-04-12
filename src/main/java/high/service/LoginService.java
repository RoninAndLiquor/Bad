package high.service;

import high.entity.Login;

public interface LoginService {

	void insert(Login login);
	
	Login queryByNameAndPwd(String userName,String password);
}
