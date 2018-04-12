package high.redisTest;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;

import redis.clients.jedis.Jedis;

public class Test {

	public static void main(String[] args) {
		Jedis jedis = new Jedis("127.0.0.1",6379);
		//Array(jedis);
		//Set(jedis);
		Map(jedis);
		
	}
	public static void Map(Jedis jedis){
		Map<String,String> map = new HashMap<String,String>();
		map.put("SCORE", "98");
		map.put("GRADE", "2");
		map.put("CLASS", "Three");
		//存入Map集合
		jedis.hmset("Express", map);
		//获取指定key Map集合的指定数据
		String hget = jedis.hget("Express", "GRADE");
		//获取指定Key Map集合的所有数据
		java.util.Map<String, String> hgetAll = jedis.hgetAll("Express");
		for(Map.Entry<String, String> s:hgetAll.entrySet()){
			System.out.println(s.getValue());
		}
	}
	public static void Set(Jedis jedis){
		jedis.sadd("teachers", "LiHui");
		Long sadd = jedis.sadd("teachers", "WangMing","LiuHuang");
		//判断当前的Set集合中是否存在Hui
		Boolean sismember = jedis.sismember("teachers", "LiHui");
		System.out.println(sismember);
		/*HashSet<String> set = new HashSet<String>();
		set.add("Remember");
		set.add("185");
		Map<String,Object> map = new HashMap<String,Object>();map.put("NO.1", "First");map.put("NO.2", "TWO");
		set.add(JSON.toJSONString(map));*/
		//获取所有的key
		java.util.Set<String> keys = jedis.keys("*");
		System.out.println("----------------------------");
		for(String str:keys){
			System.out.println(str);
		}
		//获取指定key 的set集合
		java.util.Set<String> smembers = jedis.smembers("teachers");
		System.out.println("----------------------------");
		for(String str:smembers){
			System.out.println(str);
		}
		
		/**
		 * 操作带有排序功能的set
		 */
		//根据第二个数字的大小进行排序
		jedis.zadd("treeTeachers", 5,"zhang");
		jedis.zadd("treeTeachers",1 ,"wang");
		jedis.zadd("treeTeachers",10, "li");
	}
	public static void Array(Jedis jedis){
		/*Long rpush = jedis.rpush("name", "Rose");
		System.out.println(rpush); */
		String[] str = new String[]{"ZhangSan","LiSi"};
		//放在数组的最后面
		Long rpush = jedis.rpush("names", str);
		System.out.println(rpush);
		//放入数组的最前面
		jedis.lpush("names", "WangWu");
		//移除names数组中的第一个元素
		jedis.lpop("names");
		//移除names数组中的最后一个元素
		jedis.rpop("names");
		//移除指定的元素   数字表示移除的个数   因为数组中是可以有重复元素的
		jedis.lrem("names", 2, "LiSi");
		//获取存储的数据
		List<String> lrange = jedis.lrange("names", 0, 10);
		for(String s:lrange){
			System.out.println(s);
		}
	}
	
}
