/**
 * @Description: 
 * @ClassName: com.biz.omsadmin.ConvertToObjectMapper
 * @author: Omar(OmarZhang)
 * @date: 2016年4月29日 下午11:28:25 
 */
package cn.com.bizunited.cp.oa.convert;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import cn.com.bizunited.cp.oa.commons.ConstantVariables;

/**
 * 全局日期格式转换
 * @Description: 
 * @ClassName: com.biz.omsadmin.ConvertToObjectMapper
 * @author: Omar(OmarZhang)
 * @date: 2016年4月29日 下午11:28:25 
 *
 */
public class ConvertToObjectMapper extends ObjectMapper{

	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @Description: 
	 */
	public ConvertToObjectMapper() {
		configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false); 
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(ConstantVariables.DEFAULT_GLOBAL_DATE_PATTERN);
		setTimeZone(TimeZone.getTimeZone("GMT+8"));
		setDateFormat(simpleDateFormat);
	}
	
}
