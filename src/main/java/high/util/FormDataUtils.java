package high.util;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;

import javax.activation.MimetypesFileTypeMap;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;



public class FormDataUtils {
		  
	    /** 
	     * @param args 
	     * @throws Exception 
	     */  
	    public static String sendFormData(String url,Map<String, String> parmaMap, Map<String, String> fileMap) throws Exception {  
	        trustAllHttpsCertificates();  
	        HttpsURLConnection.setDefaultHostnameVerifier(hv);  
	        return formUpload(url, parmaMap, fileMap);  
	    }  
	      
	      
	  
	    /** 
	     * 上传图片 
	     *  
	     * @param urlStr 
	     * @param textMap 
	     * @param fileMap 
	     * @return 
	     */  
	    public static String formUpload(String urlStr, Map<String, String> textMap,  
	            Map<String, String> fileMap) {  
	        String res = "";  
	        HttpURLConnection conn = null;  
	        String BOUNDARY = "---------------------------123821742118716"; //boundary就是request头和上传文件内容的分隔符  
	        try {  
	            URL url = new URL(urlStr);  
	            conn = (HttpURLConnection) url.openConnection();  
	            conn.setConnectTimeout(5000);  
	            conn.setReadTimeout(30000);  
	            conn.setDoOutput(true);  
	            conn.setDoInput(true);  
	            conn.setUseCaches(false);  
	            conn.setRequestMethod("POST");  
	            conn.setRequestProperty("Connection", "Keep-Alive");  
	            conn.setRequestProperty("User-Agent",  
	                            "Mozilla/5.0 (Windows; U; Windows NT 6.1; zh-CN; rv:1.9.2.6)");  
	            conn.setRequestProperty("Content-Type",  
	                    "multipart/form-data; boundary=" + BOUNDARY);  
	  
	            OutputStream out = new DataOutputStream(conn.getOutputStream());  
	            // text  
	            if (textMap != null) {  
	                StringBuffer strBuf = new StringBuffer();  
	                Iterator iter = textMap.entrySet().iterator();  
	                while (iter.hasNext()) {  
	                    Map.Entry entry = (Map.Entry) iter.next();  
	                    String inputName = (String) entry.getKey();  
	                    String inputValue = (String) entry.getValue();  
	                    if (inputValue == null) {  
	                        continue;  
	                    }  
	                    strBuf.append("\r\n").append("--").append(BOUNDARY).append("\r\n");  
	                    strBuf.append("Content-Disposition: form-data; name=\"" + inputName + "\"\r\n\r\n");  
	                    strBuf.append(inputValue);  
	                }  
	                out.write(strBuf.toString().getBytes());  
	            }  
	  
	            // file  
	            if (fileMap != null) {  
	                Iterator iter = fileMap.entrySet().iterator();  
	                while (iter.hasNext()) {  
	                    Map.Entry entry = (Map.Entry) iter.next();  
	                    String inputName = (String) entry.getKey();  
	                    String inputValue = (String) entry.getValue();  
	                    if (inputValue == null) {  
	                        continue;  
	                    }  
	                    File file = new File(inputValue);  
	                    String filename = file.getName();  
	                    String contentType = new MimetypesFileTypeMap().getContentType(file);  
	                    if (filename.endsWith(".png")) {  
	                        contentType = "image/png";  
	                    }  
	                    if (contentType == null || contentType.equals("")) {  
	                        contentType = "application/octet-stream";  
	                    }  
	  
	                    StringBuffer strBuf = new StringBuffer();  
	                    strBuf.append("\r\n").append("--").append(BOUNDARY).append("\r\n");  
	                    strBuf.append("Content-Disposition: form-data; name=\""  
	                            + inputName + "\"; filename=\"" + filename  
	                            + "\"\r\n");  
	                    strBuf.append("Content-Type:" + contentType + "\r\n\r\n");  
	  
	                    out.write(strBuf.toString().getBytes());  
	  
	                    DataInputStream in = new DataInputStream(  
	                            new FileInputStream(file));  
	                    int bytes = 0;  
	                    byte[] bufferOut = new byte[1024];  
	                    while ((bytes = in.read(bufferOut)) != -1) {  
	                        out.write(bufferOut, 0, bytes);  
	                    }  
	                    in.close();  
	                }  
	            }  
	  
	            byte[] endData = ("\r\n--" + BOUNDARY + "--\r\n").getBytes();  
	            out.write(endData);  
	            out.flush();  
	            out.close();  
	  
	            // 读取返回数据  
	            StringBuffer strBuf = new StringBuffer();  
	            BufferedReader reader = new BufferedReader(new InputStreamReader(  
	                    conn.getInputStream()));  
	            String line = null;  
	            while ((line = reader.readLine()) != null) {  
	                strBuf.append(line).append("\n");  
	            }  
	            res = strBuf.toString();  
	            reader.close();  
	            reader = null;  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        } finally {  
	            if (conn != null) {  
	                conn.disconnect();  
	                conn = null;  
	            }  
	        }  
	        return res;  
	    }  
	  
	    
	    static HostnameVerifier hv = new HostnameVerifier() {  
            public boolean verify(String urlHostName, SSLSession session) {  
                return true;  
            }  
        };  
          
        private static void trustAllHttpsCertificates() throws Exception {  
            javax.net.ssl.TrustManager[] trustAllCerts = new javax.net.ssl.TrustManager[1];  
            javax.net.ssl.TrustManager tm = new miTM();  
            trustAllCerts[0] = tm;  
            javax.net.ssl.SSLContext sc = javax.net.ssl.SSLContext  
                    .getInstance("SSL");  
            sc.init(null, trustAllCerts, null);  
            javax.net.ssl.HttpsURLConnection.setDefaultSSLSocketFactory(sc  
                    .getSocketFactory());  
        }  
      
        static class miTM implements javax.net.ssl.TrustManager,  
                javax.net.ssl.X509TrustManager {  
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {  
                return null;  
            }  
      
            public boolean isServerTrusted(  
                    java.security.cert.X509Certificate[] certs) {  
                return true;  
            }  
      
            public boolean isClientTrusted(  
                    java.security.cert.X509Certificate[] certs) {  
                return true;  
            }  
      
            public void checkServerTrusted(  
                    java.security.cert.X509Certificate[] certs, String authType)  
                    throws java.security.cert.CertificateException {  
                return;  
            }  
      
            public void checkClientTrusted(  
                    java.security.cert.X509Certificate[] certs, String authType)  
                    throws java.security.cert.CertificateException {  
                return;  
            }  
        }  
}
