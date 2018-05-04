package high.thread;

public class ThreadA implements Runnable {

	private Object obj;
	
	public ThreadA (Object obj) {
		this.obj = obj;
	}


	@Override
	public void run() {
		synchronized(obj){
			for(int i=65;i<=90;i++){
				System.out.print((char)i);
				obj.notify();
				try {
					obj.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}
