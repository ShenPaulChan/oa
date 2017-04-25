package cn.com.bizunited.cp.oa.exception;

import cn.com.bizunited.cp.oa.commons.AccessStatus;

public class ErrorPageException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8636099070259757882L;

	private int code;
	
	public ErrorPageException(int code){
		super(AccessStatus.getDescription(code));
		this.code = code;
	}
	
	public ErrorPageException(int code, Exception e){
		super(AccessStatus.getDescription(code), e);
		this.code = code;
	}
	
	public ErrorPageException(int code, String message){
		super(message);
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

}
