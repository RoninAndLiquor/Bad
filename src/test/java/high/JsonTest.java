package high;

import high.entity.P2pUserInfoBO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonTest {

	public static void main(String[] args){
		ObjectMapper om = new ObjectMapper();
		String str = "[{\"idNo\":\"410702199105270057\",\"idType\":\"00\",\"merCustId\":\"6000060004067904\",\"retCode\":\"0000\",\"retInfo\":\"æå\",\"userId\":\"5961\",\"usrEmail\":\"\",\"usrId\":\"xrol_20180503_15961\",\"usrMp\":\"18539449443\",\"usrName\":\"ç½ä¹¾\"}]";
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		String jsonString = JSON.toJSONString(str);
		try {
			List readValue = om.readValue(jsonString, List.class);
			System.out.println(JSON.toJSONString(readValue));
		} catch (Exception e) {
			System.err.println("error");
		}
		for(int i=0;i<list.size();i++){
			JSON.toJSONString(list.get(i));
		}
	}
	
}
