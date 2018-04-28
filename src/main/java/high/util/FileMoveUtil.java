/*
 * Copyright (C), 2015-2015, 达信财富投资管理（上海）有限公司
 * FileName: FileCodeUtil.java
 * Author:   黄健
 * Date:     2015年9月9日 下午4:58:55
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package high.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 *
 * @author huangjian
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class FileMoveUtil {

	/**
	 * 日志组件
	 */
	private static final Logger LOG = LoggerFactory.getLogger(FileMoveUtil.class);

	/**
	 * 将上传的文件复制到操作目录
	 *
	 * @param sourceUrl
	 *            源文件地址
	 * @param targetUrl
	 *            目标地址
	 * @see [相关类/方法](可选)
	 * @since [产品/模块版本](可选)
	 */
	public static void moveFile(String sourceUrl, String targetUrl) {
		Assert.notNull("** moveFile() 源文件不存在", sourceUrl);
		Assert.notNull("** moveFile() 目标地址不存在", targetUrl);
		LOG.info("**Start**UploadFileServiceImpl**moveFile() param sourceUrl={}/targetUrl={}**", sourceUrl, targetUrl);
		long start = System.currentTimeMillis();
		FileInputStream fileInputStream = null;
		FileOutputStream fileOutputStream = null;
		try {
			fileInputStream = new FileInputStream(new File(sourceUrl)); // 读入原文件
			fileOutputStream = new FileOutputStream(targetUrl);
			byte[] bufferArray = new byte[1024 * 1024];
			int length;
			while ((length = fileInputStream.read(bufferArray)) != -1) {
				fileOutputStream.write(bufferArray, 0, length);
			}
		} catch (IOException e) {
			e.printStackTrace();
			LOG.error("**moveFile() exception={}**", e.getMessage());
		} finally {
			try {
				fileInputStream.close();
				fileOutputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		long end = System.currentTimeMillis();
		LOG.info("***源文件移动使用了" + (end - start) + "毫秒。" + "***");
		LOG.info("**End**moveFile()**");
	}
}
