package cn.com.bizunited.cp.oa.exception;

import cn.com.bizunited.cp.oa.commons.AccessStatus;

/**
 * Created by Weston on 2016/8/22.
 */
public class ServerException extends RuntimeException {
    /**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = -4799587246809404733L;
	protected String messages;
	protected int httpStatus=0;
    
    public ServerException(int httpStatus, String messages){
        this.httpStatus = httpStatus;
        this.messages = messages;
    }

    /**
	 * @Description: 
	 * @param oRDER_PAYED
	 */
	public ServerException(int httpStatus) {
		this.httpStatus = httpStatus;
		this.messages = AccessStatus.getDescription(httpStatus);
	}
	
	

	public String getMessages() {
        return messages;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }

	/**
	 * @Title: getHttpStatus 
	 * @return
	 * @author: shenp
	 * @date: 2017年1月19日 下午8:23:04
	 */
	public int getHttpStatus() {
		// TODO Auto-generated method stub
		return 0;
	}
}
