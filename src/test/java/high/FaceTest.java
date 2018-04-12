package high;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class FaceTest {

	public static void main(String[] args) throws IOException {
		float f = 3;
		float l = 3.4f;
		int i = 5;
		int j = 10;
		System.out.println(++i+"  " + j++);
		System.out.println(i+"  "+j);
		int a=5;
		int b=++a;
		int c=a+(++b);
		System.out.println(2>>4);

	    String s1= "ab" + "cd";  
	    String s2= "abc" + "d";  
	    int hashCode = s2.hashCode();
	    System.out.println(s1 == s2);
	    System.out.println(hashCode);
	    String str = "[123]";
	    System.out.println(str.substring(1, str.length()-1));
	    String stri = null;
	  /*  if(stri.startsWith("{")){
	    	System.out.println(true);
	    }*/
	    HashMap<String,Object> hashMap = new HashMap<String,Object>();
	    hashMap.put("dd", 45);
	    ConcurrentHashMap<String,Object> map = new ConcurrentHashMap<String,Object>();
	    int o = -1;
	    int p = o>>3;
	    System.out.println("P="+p);
	    Object put = map.put("ss", 13);
	    map.get("dd");
	    Object remove = map.remove("dd");
	    map.size();
	    boolean b1 = 9>=15;
	    System.out.println(b1);
	    
	    int [] int1 = new int[10];
	    System.out.println(int1.length);
	    
	    Float pi = new Float(3.14);
	    if(pi>3){
	    	System.out.println("pi 大于 3");
	    }else{
	    	System.out.println("pi 小于 3");
	    }
	    File f1 = new File("ff");
	    try {
			OutputStream os = new FileOutputStream("F://test.txt",true);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    try {
			Reader r = new FileReader(new File(""));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    InputStream is = new FileInputStream(new File("F://test.txt"));
	    InputStream bis = new BufferedInputStream(is);
	    int rea = -1;
	    byte[] by = new byte[128];
	    while((rea=bis.read(by))!=-1){
	    	System.out.println(new String(by));
	    }
	    System.out.println();
	    System.out.println();
	    System.out.println("-------------------------------------------------------");
	    System.out.println();
	    System.out.println();
	    //int sum = 15*1.5;
	    String s = "aaa";
	    String as = addStr(s);
	    System.out.println(s);
	   /* StringBuffer sb = new StringBuffer("aaa");
	    appendSb(sb);
	    System.out.println(sb);
	    System.out.println(new Date());*/
	    //当声明一个ArrayList的时候 当前默认的容量为0  size也为0 
	    //当使用添加方法添加元素的时候 首先会判断当前的ArrayList是否等于默认值 也就是 空数组 
	    //如果当前的ArrayList是空数组的话  当前的size+1会和ArrayList的默认初始容量10 进行比较
	    //谁最大谁就会设置为当前ArrayList的容量
	    //也就是说 ，当使用了add方法之后才会设置ArrayList的初始容量10
	    List<String> list = new ArrayList<String>();
	    list.add("");
	    list.add("");
	    System.out.println(11);
	    int n = 3>>2;
	    Map<String,Object> myMap = new ConcurrentHashMap<String,Object>();
	    
	}
	public static String addStr(String s){
		return s+="bbb";
	}
	
	public static StringBuffer appendSb(StringBuffer sb){
		return sb.append("bbb");
	}
	
}
