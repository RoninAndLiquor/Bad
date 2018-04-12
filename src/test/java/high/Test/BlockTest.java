package high.Test;

import java.util.*;

import high.entity.User;

public class BlockTest {

	public static void main(String[] args) {
		User user = new User();
		Map<String,Object> hashMap = new HashMap<String,Object>();
		Map<String,Object> linkedMap = new LinkedHashMap<String,Object>();
		Map<String,Object> map = new TreeMap<String,Object>();
		Map<String,Object> hashTable = new Hashtable<String,Object>();
		hashTable.put("cfd", 123);
		hashTable.put("256", 456);
		hashTable.put("abd", 12);
		hashTable.put("389", 12);
		hashTable.put("bbb", 78);
		System.out.println(hashTable);
	}
	
}
