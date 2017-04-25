/**
 * @Description: 
 * @ClassName: cn.com.bizunited.cp.oa.utils.AdminLoginUtil
 * @author: shenp
 * @date: 2017年1月22日 下午5:24:16 
 */
package cn.com.bizunited.cp.oa.utils;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 * @Description: 
 * @ClassName: cn.com.bizunited.cp.oa.utils.AdminLoginUtil
 * @author: shenp
 * @date: 2017年1月22日 下午5:24:16 
 *
 */
public class AdminLoginUtil {

	public static String getCurrentName() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            String principal = (String) subject.getPrincipal();
            return principal;
        }
        return null;
    }
	
}
