package high;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import com.alibaba.fastjson.JSON;

public class ThreadPoolTest {

	final static int PRE_NUM = 50;
	
	public static void main(String[] args) throws InterruptedException {
		long startTime = System.currentTimeMillis();
		int count = 5060;
		CountDownLatch latch = new CountDownLatch(count);
		ExecutorService es = Executors.newCachedThreadPool();
		LinkedBlockingQueue<Map<String,Object>> queue = new LinkedBlockingQueue<Map<String,Object>>();
		List<Map<String,Object>> list=  new ArrayList<Map<String,Object>>();
		for(int i=1;i<=count;i++){
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("NO."+i, "VAL : "+i);
			queue.put(map);
			list.add(map);
		}
		int n = queue.size()/PRE_NUM;
		int m = queue.size()%PRE_NUM>0?n+1:n;
		Thread.sleep(200);
		for(int i=0;i<m;i++){
			es.execute(new Runnable(){
				public void run(){
					while(!queue.isEmpty()){
						try {
							System.out.println(Thread.currentThread().getName()+" -- "+JSON.toJSONString(queue.take()));
							latch.countDown();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					/*for(int i=0;i<list.size();i++){
						System.out.println(Thread.currentThread().getName()+" -- "+JSON.toJSONString(list.get(i)));
						latch.countDown();
					}*/
				}
			});
		}
		es.shutdown();
		latch.await();
		/*for(int i=0;i<count;i++){
			while(!queue.isEmpty()){
				System.out.println(queue.take());
			}
		}*/
		System.out.println("用时："+(System.currentTimeMillis()-startTime));
	}
	
}
