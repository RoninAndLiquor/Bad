/*
 * Copyright (C), 2014-2017, 达信财富投资管理（上海）有限公司
 * FileName: MD5Util.java
 * Author:   FlaTa
 * Date:     2017年2月28日 下午3:09:21
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package high.util;

import java.security.MessageDigest;

/**
 * 〈一句话功能简述〉<br> 
 * 〈功能详细描述〉
 *
 * @author FlaTa
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class MD5Util {
	private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5",
		"6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };
	
	/**
	 * 转换字节数组为16进制字串
	 * 
	 * @param b
	 *            字节数组
	 * @return 16进制字串
	 */
	public static String byteArrayToHexString(byte[] b) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			resultSb.append(byteToHexString(b[i]));
		}
		return resultSb.toString();
	}
	
	/**
	 * J 转换byte到16进制
	 * 
	 * @param b
	 * @return
	 */
	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0) {
			n = 256 + n;
		}
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}
	
	/**
	 * J 编码
	 * 
	 * @param origin
	 * @return
	 */
	
	// MessageDigest 为 JDK 提供的加密类
	public static String MD5Encode(String origin) {
		origin =origin.trim();
		String resultString = null;
		try {
			resultString = new String(origin);
			MessageDigest md = MessageDigest.getInstance("MD5");
			resultString = byteArrayToHexString(md.digest(resultString
					.getBytes("UTF-8")));
		} catch (Exception ex) {
		}
		return resultString;
	}
	
	public static String MD5Encode(byte[] bytes) {
		String resultString = null;
		try {
	//		resultString = new String(origin);
			MessageDigest md = MessageDigest.getInstance("MD5");
			resultString = byteArrayToHexString(md.digest(bytes));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return resultString;
	}
	
	// MessageDigest 为 JDK 提供的加密类
	public static String MD5Encode(String origin,String charsetName) {
		origin =origin.trim();
		String resultString = null;
		try {
			resultString = new String(origin);
			MessageDigest md = MessageDigest.getInstance("MD5");
			resultString = byteArrayToHexString(md.digest(resultString
					.getBytes(charsetName)));
		} catch (Exception ex) {
		}
		return resultString;
	}
}
