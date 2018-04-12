package high.controller;

import io.swagger.annotations.Api;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.multipart.MultipartFile;

import com.couchbase.client.deps.com.fasterxml.jackson.core.JsonGenerationException;
import com.couchbase.client.deps.com.fasterxml.jackson.databind.JsonMappingException;
import com.couchbase.client.deps.com.fasterxml.jackson.databind.ObjectMapper;

@Api(description = "文件上传")
@Controller
@RequestMapping("fileUpload")
public class FileUpload {
	
	@RequestMapping(value= "getFilePath.json",method = RequestMethod.POST)
	@ResponseBody
	public String getFilePath(@RequestParam("file") MultipartFile file,HttpSession session ){
		String finalPath = null;
		if(!file.isEmpty()){
			String realPath = session.getServletContext().getRealPath("/")+"\\img\\";
			System.out.println("*** realPath ***"+realPath);
			String originalFilename = file.getOriginalFilename();
			String subfix = originalFilename.substring(originalFilename.indexOf("."));
			UUID randomUUID = UUID.randomUUID();
			finalPath = realPath+randomUUID+subfix;
			try {
				BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File(finalPath)));
				out.write(file.getBytes());
				out.flush();
				out.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return finalPath;
	}
	
	@RequestMapping(value = "getTest.json",method = RequestMethod.POST)
	@ResponseBody
	public String getTest(){
		return new Date().toString();
	}
	@RequestMapping(value = "getAllFileName.json")
	@ResponseBody
	public String getAllFileName(HttpServletRequest request){
		String requestURI = request.getRequestURI();
		String remoteHost = request.getRemoteHost();
		String localName = request.getLocalName();
		Principal userPrincipal = request.getUserPrincipal();
		String protocol = request.getProtocol();
		String remoteUser = request.getRemoteUser();
		File[] listRoots = File.listRoots();
		for(File f : listRoots){
			File file = f;
			List<File> fileList = new ArrayList<File>();
			getFile(file,fileList);
			ObjectMapper om = new ObjectMapper();
			Map<String,Object> map = new HashMap<String,Object>();
			List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
			String str = "";
			for(File fl:fileList){
				map.put(fl.getName()+" * "+(int)Math.random()*100, fl.toPath().toString());
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
		}
		return "SUCCESS";
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
