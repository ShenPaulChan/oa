package cn.com.bizunited.cp.oa.utils;

import org.apache.commons.lang.StringUtils;

/**
 * 字符串操作工具
 * @author isshu
 *
 */
public class StringUtil {
	/**
	 * 字符串为空 或是空字符串都认为是空
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str){
		return StringUtils.isEmpty(str) || "".equals(str.trim());
	}
	
	public static boolean isNotEmpty(String str){
		return !isEmpty(str);
	}
}
