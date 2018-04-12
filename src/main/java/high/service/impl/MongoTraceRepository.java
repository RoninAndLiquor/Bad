package high.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.boot.actuate.trace.Trace;
import org.springframework.boot.actuate.trace.TraceRepository;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
/**
 * 
 * @包名 :high.service.impl
 * @文件名 :MongoTraceRepository.java 
 * TODO 类作用：
 * @系统名称 : 保存trace跟踪信息
 * @Author: 蒋帅锋 
 * @Date: 2018年3月15日 上午10:52:37
 * @版本号   :v0.0.01
 */

public class MongoTraceRepository implements TraceRepository {
	
	@Override
	public void add(Map<String, Object> arg0) {
		File file = new File("F://trace.txt");
		if(file.exists()){
			writeFile(file,arg0);
		}else{
			try {
				file.createNewFile();
				writeFile(file,arg0);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void writeFile(File file,Map<String,Object> map){
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(file,true);
			String jsonString = JSON.toJSONString(map);
			fos.write(jsonString.getBytes());
			fos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(fos!=null){
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	@Override
	public List<Trace> findAll() {
		return  null;
	}

}
