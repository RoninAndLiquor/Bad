package high.thread;


public class ThreadNumber implements Runnable {

	private Object obj;

	public ThreadNumber(Object obj){
		this.obj = obj;
	}

	@Override
	public void run() {
		synchronized(obj){
			for(int i=1;i<=52;i++){
				System.out.print(i);
				if(i%2==0){
					try {
						obj.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				obj.notify();
			}
		}
	}

}
