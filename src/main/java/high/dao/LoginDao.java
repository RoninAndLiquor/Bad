
package high.dao;

import high.entity.Login;

public interface LoginDao {

	void insert(Login login);
	
	Login queryByNameAndPwd(String userName,String password);
	
}
