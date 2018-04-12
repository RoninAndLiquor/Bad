package high;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapTest2 {

	public static void main(String[] args) throws InterruptedException {
		 Map<String, Object> map1 = new HashMap<String, Object>();  
	        writeMap(map1);  
	        printMap(map1);  
	        Thread.sleep(100);
	        System.err.println("-------------------------------");  
	  
	        Map<String, Object> map2 = Collections.synchronizedMap(new TreeMap<String, Object>());  
	        writeMap(map2);  
	        printMap(map2);  
	        Thread.sleep(100);
	        System.err.println("-------------------------------");  
	  
	        Map<String, Object> map3 = new ConcurrentHashMap<String, Object>();  
	        writeMap(map3);  
	        printMap(map3);  
	}
	
	private static void writeMap(Map<String, Object> map) {  
        for (int i = 0; i < 10; i++) {  
            map.put("key" + i, "value" + i);  
        }  
    }  
  
    private static void printMap(Map<String, Object> map) {  
        for (Map.Entry<String, Object> entry : map.entrySet()) {  
            System.out.println("key: " + entry.getKey() + ", value: " + entry.getValue());  
        }  
    }  
	
}
