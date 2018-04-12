package high;

import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MapTest {
	
	class myRun implements Runnable{

		private CountDownLatch count;
		
		private Map<String,Object> map;
		
		private Integer i;
		
		public myRun(CountDownLatch count,Map<String,Object> map,Integer i){
			this.count = count;
			this.map = map;
			this.i = i;
		}
		
		@Override
		public void run() {
			for(int j = 0;j<i;j++){
				map.put("KEY  "+j, Thread.currentThread().getName()+" VAL  "+j);
				count.countDown();
			}
		}
		
	}

	public static void main(String[] args) throws InterruptedException {
		int count = 1200;
		CountDownLatch latch = new CountDownLatch(count);
		//Map<String,Object> map = new HashMap<String,Object>();
		//Map<String,Object> map = new Hashtable<String,Object>();
		//Map<String,Object> map = new ConcurrentHashMap<String,Object>();
		final Map<String,Object> map = Collections.synchronizedMap(new HashMap<String,Object>());
		ExecutorService es = Executors.newFixedThreadPool(count);
		//es.execute(new MapTest().new myRun(latch,map,count));
		/*es.execute(new Runnable(){

			@Override
			public void run() {
				map.put("ss", 12);
				
			}
			
		});*/
		Thread[] thread = new Thread[count];
		for(int i=0;i<count;i++){
			final int flag = i;
			thread[i] = new Thread(new Runnable(){
				@Override
				public void run() {
					map.put("  KK  "+flag, Thread.currentThread().getName()+"  VV  "+flag);
				}
				
			});
		}
		for(int i=0;i<count;i++){
			thread[i].start();
		}
		//es.shutdown();
		//latch.wait();
		Thread.sleep(3000);
		for(Map.Entry<String, Object> m: map.entrySet()){
			System.out.println(m.getKey()+"  =  "+m.getValue());
		}
		System.out.println(map.size());
		Integer sec = 0;
	}
}
