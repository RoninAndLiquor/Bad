/*
 * Copyright (C), 2014-2017, 达信财富投资管理（上海）有限公司
 * FileName: HttpClientUtils.java
 * Author:   FlaTa
 * Date:     2017年3月3日 上午11:09:54
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package high.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * 〈一句话功能简述〉<br> 
 * 〈功能详细描述〉
 *
 * @author FlaTa
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class HttpClientUtils {

	public static HttpResponse sendPostAndGetResponse(String url, Map<String, String> map) throws Exception{
		//转换参数map
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();    
        for (String key : map.keySet()) {    
            nameValuePairs.add(new BasicNameValuePair(key, map.get(key)));    
        }
        
        HttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost(url);
        post.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
        HttpResponse resp = client.execute(post);
        return resp;
	}
	
	/**
	  * 
	  * 功能描述:<br>
	  * 发送Post请求
	  *
	  * @param shenwei
	  * @see [相关类/方法](可选)
	  * @since [产品/模块版本](可选)
	  */
	public static String sendHttpPost(HttpPost httpPost) throws Exception {    
        CloseableHttpClient httpClient = null;    
        CloseableHttpResponse response = null;    
        HttpEntity entity = null;    
        String responseContent = null;    
        try {    
            // 创建默认的httpClient实例.    
            httpClient = HttpClients.createDefault();    
            // 执行请求    
            response = httpClient.execute(httpPost);    
            entity = response.getEntity();    
            responseContent = EntityUtils.toString(entity, "UTF-8");   
        } catch (Exception e) {    
            throw e;
        } finally {    
            try {    
                // 关闭连接,释放资源    
                if (response != null) {    
                    response.close();    
                }    
                if (httpClient != null) {    
                    httpClient.close();    
                }    
            } catch (IOException e) {    
                throw e;   
            }    
        }    
        return responseContent;    
    }
}
