/**
 * @Description: 
 * @ClassName: cn.com.bizunited.cp.pay.sign.MD5
 * @author: Paul Chen
 * @date: 2016年11月4日 下午1:45:26 
 */
package cn.com.bizunited.cp.oa.sign;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;

import cn.com.bizunited.cp.oa.commons.AccessStatus;
import cn.com.bizunited.cp.oa.exception.ServerException;
import cn.com.bizunited.cp.oa.utils.StringUtil;


/**
 * @Description: 
 * @ClassName: cn.com.bizunited.cp.pay.sign.MD5
 * @author: Paul Chen
 * @date: 2016年11月4日 下午1:45:26 
 *
 */
public class MD5Sign {
	
	private final static String charset = "UTF-8";
	
	
	/**
	 * MD5加密
	 * @Title: sign 
	 * @param params
	 * @param key
	 * @return
	 * @author: Paul Chen
	 * @date: 2016年11月4日 下午1:57:31
	 */
	public static String sign(Map<String, String> params, String key){
		String text = key + getParamContent(params) + key;
		byte[] textBytes = null;;
		try {
			textBytes = text.getBytes(charset);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new ServerException(AccessStatus.SIGN_ILLEGAL);
		}
		return DigestUtils.md5Hex(textBytes).toUpperCase();
	}
	
	/**
	 * 校验签名是否正确
	 * @Title: verify 
	 * @param params
	 * @param sign
	 * @param key
	 * @return
	 * @author: Paul Chen
	 * @date: 2016年11月4日 下午2:01:29
	 */
	public static boolean verify(Map<String, String> params, String sign, String key){
		String newSign = sign(params, key);
		if(newSign.equals(sign)){
			return true;
		}
		return false;
	}
	
	/**
	 * 过滤空参数和sign
	 * @Title: paraFilter 
	 * @param params
	 * @return
	 * @author: Paul Chen
	 * @date: 2016年11月16日 上午11:36:33
	 */
	private static Map<String, String> paraFilter(Map<String, String> params){
		Map<String, String> result = new HashMap<String, String>();

        if (params == null || params.size() <= 0) {
            return result;
        }

        for (String key : params.keySet()) {
            String value = params.get(key);
            if (StringUtil.isEmpty(value) || key.equalsIgnoreCase("sign")) {
                continue;
            }
            result.put(key, value);
        }

        return result;
	}
	
	/**
	 * 获取参数的拼接字符串
	 * @Title: getParamContent 
	 * @param params
	 * @return
	 * @author: Paul Chen
	 * @date: 2016年11月4日 下午1:57:40
	 */
	private static String getParamContent(Map<String, String> params){
		params = paraFilter(params);
		List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);

        String prestr = "";

        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key);
            
        	if (i == keys.size() - 1) {//拼接时，不包括最后一个&字符
                prestr = prestr + key + "=" + value;
            } else {
                prestr = prestr + key + "=" + value + "&";
            }
        }
        return prestr;
	}

}
