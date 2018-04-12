package high.concurrentMapTest;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.alibaba.fastjson.JSON;

public class Test {

	public static void main(String[] args) throws InterruptedException{
		final Map<String,Object> sysMap = new ConcurrentHashMap<String,Object>();
		final Map<String,Object> map = new HashMap<String,Object>();
		Thread[] t = new Thread[100];
		for(int i=0;i<t.length;i++){
			final int flag = i;
			t[i] = new Thread(new Runnable(){
				public void run(){
					sysMap.put(flag+"", "D");
				}
			});
		}
		for(int i=0;i<t.length;i++){
			t[i].start();
		}
		//Thread.sleep(1000);
		System.out.println(JSON.toJSONString(sysMap).split(",").length);
	}
	
}
