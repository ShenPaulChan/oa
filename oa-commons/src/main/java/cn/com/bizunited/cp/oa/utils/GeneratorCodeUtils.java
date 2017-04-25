package cn.com.bizunited.cp.oa.utils;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;



/**
 * 编码生成工具类
 * @Description: 
 * @ClassName: com.biz.omsadmin.util.GeneratorCodeUtils
 * @author: Omar(OmarZhang)
 * @date: 2016年5月22日 下午12:29:15 
 *
 */
public final class GeneratorCodeUtils {
	
	/** 自增长编码*/
	private static volatile AtomicInteger AUTO_INCREMENT = new AtomicInteger(1);
	/** 最高位 生成长度*/
	private static  Integer MAX_HI = 999;
	
	/**
	 * 生成唯一性编码CODE
	 * @Title: code 
	 * @param markCode 机器标志码(主要为了以后的集群)
	 * @param projectMarkCode 项目标识码(主要分区项目)
	 * @return markCode+projectMarkCode+generatorCode
	 * @author: Omar(OmarZhang)
	 * @date: 2016年5月22日 下午4:04:50
	 */
	public static String code(String pre) {
		if(AUTO_INCREMENT.get() > MAX_HI ) {
			AUTO_INCREMENT.set(1);
		} 
		int aotuIncrement = AUTO_INCREMENT.incrementAndGet();
		return new StringBuffer().append(pre).append(getTimeHashCode()).append(getUUIDHashCode()).append(aotuIncrement).toString();
	}
	
	
	/**
	 * UUID 码 HashCode 截取长度 0 ~4 
	 * @Title: getUUIDHashCode 
	 * @return
	 * @author: Omar(OmarZhang)
	 * @date: 2016年5月22日 下午4:06:14
	 */
	private static String getUUIDHashCode() {
		int hashCode = UUID.randomUUID().hashCode();
		if(hashCode < 0) {
			hashCode = - hashCode;
		}
		return String.valueOf(hashCode).substring(0, 4);
	}
	
	/**
	 * 日期生成码 截取长度 从3开始到结尾
	 * @Title: getTimeHashCode 
	 * @return
	 * @author: Omar(OmarZhang)
	 * @date: 2016年5月22日 下午4:06:42
	 */
	private static String getTimeHashCode() {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddhhmmss");
		String timeCodeStr = String.valueOf(format.format(new Date()).hashCode());
		return timeCodeStr.substring(3, timeCodeStr.length());
	}
	public static void main(String[] args) {
		/*ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(100, 100000, 6000, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
		final Map<String,Integer> tempMap = new ConcurrentHashMap<String,Integer>(); 
		for(int i =0 ;i<100;i++) {
			poolExecutor.execute(new Runnable() {
				public void run() {
					String key = GeneratorCodeUtils.code(100, 200);
					tempMap.put(key, tempMap.get(key) == null ? 1 : tempMap.get(key)+1);
					System.out.println(tempMap);
				}
			});
		}*/
		System.out.println(GeneratorCodeUtils.code("Pro"));;
		
	}

}
