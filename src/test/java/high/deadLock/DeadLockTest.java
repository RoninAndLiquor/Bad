package high.deadLock;

public class DeadLockTest {

	private static String A = "A";
	private static String B = "B";
	
	public static void main(String[] args){
		deadLock();
	}
	public static void deadLock(){
		Thread t1 = new Thread(new Runnable(){

			@Override
			public void run() {
				synchronized(A){
					System.out.println("t1进入A");
					synchronized(B){
						System.out.println("t1进入B");
					}
				}
			}
			
		});
		Thread t2 = new Thread(new Runnable(){

			@Override
			public void run() {
				synchronized(B){
					System.out.println("t2进入B");
					synchronized(A){
						System.out.println("t2进入A");
					}
				}
			}
			
		});
		t1.start();
		t2.start();
	}
	
}
