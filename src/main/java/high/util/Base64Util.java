package high.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;

public class Base64Util {
	
    private static final String charset = "utf-8";

    /**
     * 解密
     * 
     * @param data
     * @return
     * @author jqlin
     */
    public static String decode(String data) {
        try {
            if (null == data) {
                return null;
            }
            
            return new String(Base64.decodeBase64(data.getBytes(charset)), charset);
        } catch (UnsupportedEncodingException e) {
        }

        return null;
    }

    /**
     * 加密
     * 
     * @param data
     * @return
     * @author jqlin
     */
    public static String encode(String data) {
        try {
            if (null == data) {
                return null;
            }
            return new String(Base64.encodeBase64(data.getBytes(charset)), charset);
        } catch (UnsupportedEncodingException e) {
        }

        return null;
    }
	
}
