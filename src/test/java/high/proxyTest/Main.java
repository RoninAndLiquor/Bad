package high.proxyTest;

public class Main {

	public static void main(String[] args) {
		
		/*UserDao target = new UserDaoImpl();
		UserDaoProxy proxy = new UserDaoProxy(target);
		proxy.save();*/
		
		
		/*DynamicProxy proxy = new DynamicProxy();
		UserDao instance = (UserDao) proxy.getInstance(new UserDaoImpl());
		instance.save();
		instance.delete();*/
		//DataSourceAutoConfiguration
		UserDao target = new UserDaoImpl();
		CglibProxy proxy = new CglibProxy(target);
		UserDao instance = (UserDao) proxy.getIntence();
		String save = instance.save();
		System.out.println(save);
	}
	
}
