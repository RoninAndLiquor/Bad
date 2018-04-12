package high.proxyTest;

public class UserDaoImpl implements UserDao {

	@Override
	public String save() {
		// TODO Auto-generated method stub
		System.out.println("Data already save!");
		return "Save";
	}

	@Override
	public void delete() {
		System.out.println("Data already delete!");
	}

}
