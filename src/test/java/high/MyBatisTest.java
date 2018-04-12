package high;

import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.hadoop.mapred.gethistory_jsp;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.web.context.WebApplicationContext;

import com.alibaba.fastjson.JSON;

public class MyBatisTest {

	public static void main(String[] args) throws IOException {
		/*Reader reader = Resources.getResourceAsReader("classpath:");
		SqlSessionFactory sqlSession = new SqlSessionFactoryBuilder().build(reader);
		SqlSession openSession = sqlSession.openSession();*/
		Integer i = 2222;
		Integer j = 2222;
		Integer k = 127;
		Integer l = 127;
		System.out.println("i==j? "+(i==j));
		System.out.println("k==l? "+(k==l));
		System.out.println(3<<2);
		System.out.println(4>>1);
		List<Map<String,Object>> list=  new ArrayList<Map<String,Object>>();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("FIRST", 123);
		map.put("SECOND", 465);
		map.put("THREE", 789);
		list.add(map);
		Map<String,Object> map2 = new HashMap<String,Object>();
		map2.put("FIRST", "RONIN");
		map2.put("SECOND", "GAINT");
		map2.put("THREE", "UNUSUAL");
		list.add(map2);
		Map<String,Object> map3 = new HashMap<String,Object>();
		map2.put("FIRST", "THIS IS FIRST");
		map2.put("SECOND", "MY NAME IS SECOND");
		map2.put("THREE", "THAT");
		BigDecimal bd = new BigDecimal(10.00f);
		map3.put("MONEY", bd);
		list.add(map3);
		System.out.println(JSON.toJSONString(list));
		list.forEach(n ->{
			
			String string = n.get("FIRST").toString();
			System.out.println(string);
			
		});
		/*String collect = list.stream().
				map(t->t.get("FIRST").toString()).
				collect(Collectors.joining(","));
		System.out.println(collect);*/
	}
	
}
