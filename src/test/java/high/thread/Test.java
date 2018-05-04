package high.thread;

public class Test {

	public static void main(String[] args){
		Object obj = new Object();
		ThreadNumber threadNumber = new ThreadNumber(obj);
		ThreadA threadA = new ThreadA(obj);
		new Thread(threadNumber).start();
		new Thread(threadA).start();
		
		/*NotifyOne one = new NotifyOne(obj);
		one.setName(" one ");
		NotifyTwo two = new NotifyTwo(obj);
		two.setName(" two ");
		one.start();
		two.start();*/
		
		int n = 0xFFFF;
		System.out.println(n);
	}
	
}
