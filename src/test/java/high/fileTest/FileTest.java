package high.fileTest;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.couchbase.client.deps.com.fasterxml.jackson.core.JsonGenerationException;
import com.couchbase.client.deps.com.fasterxml.jackson.databind.JsonMappingException;
import com.couchbase.client.deps.com.fasterxml.jackson.databind.ObjectMapper;

public class FileTest {
	public static void main(String[] args){
		
		File file = new File("F:/");
		List<File> fileList = new ArrayList<File>();
		getFile(file,fileList);
		ObjectMapper om = new ObjectMapper();
		Map<String,Object> map = new HashMap<String,Object>();
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		String str = "";
		for(File f:fileList){
			if(f.getName().endsWith(".avi")||f.getName().endsWith(".mp4")||f.getName().endsWith(".rmvb")){
				System.out.println(f.toPath().toString());
			}
			map.put(f.getName()+" * "+(int)Math.random()*100, f.toPath().toString());
		}
		try {
			File txtFile = new File("F:/test.txt");
			om.writeValue(txtFile, map);
			/*String string=" attrib +H  "+txtFile.getAbsolutePath(); //设置文件属性为隐藏
	        Runtime.getRuntime().exec(string);*/
			
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*File[] listRoots = File.listRoots();
		for(File f : listRoots){
			System.out.println(f.toPath());
		}*/
		
		
	}
	
	private static void getFile(File file,List<File> fileList){
		File[] listFiles = file.listFiles();
		if(listFiles!=null&&listFiles.length>0){
			for(File f:listFiles){
				if(f.isDirectory()){
					getFile(f,fileList);
				}else{
					fileList.add(f);
				}
			}
		}
	}
}
