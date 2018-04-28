package high.util;


import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class PathUtil {
	
	/**
	 * 获取web项目的Web Content 目录
	 * @return
	 */
	public static String webContentPath(){
		try {
			String path = PathUtil.class.getResource("").getFile();
			path = URLDecoder.decode(path, "UTF-8");
			if (path.lastIndexOf("/WEB-INF") > -1) {
				path = path.substring(0, path.lastIndexOf("/WEB-INF"));
				String temp = path.substring(0, 5);
				if ("file:".equalsIgnoreCase(temp)) {
					if (path.lastIndexOf("/") > -1) {
						path = path.substring(5);
					}
				}
			}
			return path;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	/**
	 * 获取类的绝对路径
	 * @param clazz
	 * @return
	 */
	public static String getClassAbsolutePath(Class<?> clazz) {
		return clazz.getResource("").getPath();
	}
	
	/**
	 * 获取类的绝对路径
	 * @param clazz
	 * @return
	 */
	public static String getClassBuildPath() {
		return PathUtil.class.getClassLoader().getResource("").getPath();
	}
	/**
	 * 获取工程路径
	 * @return
	 */
	public static String getProjectPath() {
		return System.getProperty("user.dir");
	}
	
//	/*
//	 * 获取Web-Info 下的 resource 资源文件夹路径
//	 */
//	public static String getResourcePath(){
//		String fontPath = PathUtil.getClassBuildPath();
//    	fontPath = fontPath.substring(0,fontPath.indexOf("classes"))+"resource/";
//    	return fontPath;
//	}
	
//	public static String getFileName(String path){
//		return path.substring(path.lastIndexOf(File.separator)+1);
//	}
	
	/*
     * 获取Web-Info 下的Freemarker  资源文件夹路径
     */
    public static String getFreemarkerPath(){
        String fontPath = PathUtil.getClassBuildPath();
        fontPath = fontPath.substring(0,fontPath.indexOf("classes"))+"freemarker/";
        return fontPath;
    }
	
}
