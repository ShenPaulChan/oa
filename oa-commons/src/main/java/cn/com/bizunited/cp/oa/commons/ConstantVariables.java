package cn.com.bizunited.cp.oa.commons;

/**
 * 常用变量
 * @Description: 
 * @ClassName: com.fashionshow.common.constant.ConstantVariables
 * @author: Omar(zp)
 * @date: 2015年8月6日 上午1:14:51 
 *
 */
public class ConstantVariables {
	
	/** 登录标识*/
	public static final String ADMIN_LOGIN_SIGN = "admin_login_sign"; 
	/** 登录失败次数*/
	public static final Integer LOGIN_FAIL_COUNT = 5;
	/** 登录失败 锁定时间*/
	public static final Integer LOGIN_FAIL_TIME = 10;
	/** xml 后缀名*/
	public static final String XML_SUFFIX_TYPE = ".XML";
	/** 日期格式配比 */
	public static final String[] DATE_PATTERNS = new String[] { "yyyy", "yyyy-MM", "yyyyMM", "yyyy/MM", "yyyy-MM-dd", "yyyyMMdd", "yyyy/MM/dd", "yyyy-MM-dd HH:mm:ss", "yyyyMMddHHmmss", "yyyy/MM/dd HH:mm:ss" };
	/** 默认全局日期格式*/
	public static final String DEFAULT_GLOBAL_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";
	
	private ConstantVariables() {
	}
}
