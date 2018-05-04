package high.thread;

public class NotifyTwo extends Thread{

	private Object obj;
	
	public NotifyTwo(Object obj){
		this.obj =obj;
	}
	
	public void run(){
		synchronized(obj){
			System.out.println(Thread.currentThread().getName()+"准备唤醒线程");
			obj.notify();
			try {
				System.out.println(Thread.currentThread().getName()+"准备进入等待");
				obj.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
