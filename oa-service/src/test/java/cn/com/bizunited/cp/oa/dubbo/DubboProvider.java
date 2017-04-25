/**
 * @Description: 
 * @ClassName: cn.com.bizunited.cp.oa.dubbo.DubboProvider
 * @author: shenp
 * @date: 2017年1月20日 下午3:04:24 
 */
package cn.com.bizunited.cp.oa.dubbo;

import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;



/**
 * @Description: 
 * @ClassName: cn.com.bizunited.cp.oa.dubbo.DubboProvider
 * @author: shenp
 * @date: 2017年1月20日 下午3:04:24 
 *
 */
public class DubboProvider {
	
	private static Logger logger = Logger.getLogger(DubboProvider.class);
	
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
            "classpath*:/config/applicationContext-datasource.xml",
            "classpath*:/config/applicationContext.xml",
            "classpath*:/config/applicationContext-dubbo.xml");
		context.start();
		synchronized (DubboProvider.class) {
			while (true) {
				try {
					DubboProvider.class.wait();
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
				}
				
			}
		}
	}

}
