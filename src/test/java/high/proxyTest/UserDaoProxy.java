package high.proxyTest;

public class UserDaoProxy implements UserDao {

	private UserDao target;
	
	 public UserDaoProxy(UserDao target){
		 this.target = target;
	 }
	
	@Override
	public String save() {
		System.out.println("Target method before handle!");
		String save = target.save();
		System.out.println("Target method after handle!");
		return save;
	}

	@Override
	public void delete() {
		System.out.println("Target method before handle!");
		target.delete();
		System.out.println("Target method after handle!");
	}

}
