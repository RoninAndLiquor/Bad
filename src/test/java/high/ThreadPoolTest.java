package high;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import com.alibaba.fastjson.JSON;

public class ThreadPoolTest {

	final static int PRE_NUM = 1000;
	
	public static void main(String[] args) throws InterruptedException {
		int count = 100000;
		CountDownLatch latch = new CountDownLatch(count);
		ExecutorService es = Executors.newCachedThreadPool();
		ConcurrentLinkedQueue<Map<String,Object>>	queue =result(count);
		long startTime = System.currentTimeMillis();
		int n = queue.size()/PRE_NUM;
		int m = queue.size()%PRE_NUM>0?n+1:n;
		m=16;
		//Thread.sleep(200);
		for(int i=0;i<m;i++){
			es.execute(new Runnable(){
				public void run(){
					while(!queue.isEmpty()){
						System.out.println(Thread.currentThread().getName()+" -- "+JSON.toJSONString(queue.poll()));
						latch.countDown();
					}
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
	public static ConcurrentLinkedQueue<Map<String,Object>> result(int count) throws InterruptedException{
		ConcurrentLinkedQueue<Map<String,Object>> queue = new ConcurrentLinkedQueue<Map<String,Object>>();
		List<Map<String,Object>> list=  new ArrayList<Map<String,Object>>();
		for(int i=1;i<=count;i++){
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("NO."+i, "VAL : "+i);
			queue.add(map);
			list.add(map);
		}
		return queue;
	}
	
}
