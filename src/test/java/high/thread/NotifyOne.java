package high.thread;

public class NotifyOne extends Thread{

	private Object obj;
	
	public NotifyOne(Object obj){
		this.obj = obj;
	}
	
	public void run(){
		synchronized(obj){
			try {
				System.out.println(Thread.currentThread().getName()+"准备进入等待");
				obj.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName()+"准备唤醒线程");
			obj.notify();
		}
	}
	
}
