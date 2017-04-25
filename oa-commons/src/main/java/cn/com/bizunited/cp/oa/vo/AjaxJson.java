package cn.com.bizunited.cp.oa.vo;

import java.io.Serializable;

import cn.com.bizunited.cp.oa.commons.AccessStatus;


public class AjaxJson implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3758309468353265291L;
	
	private int code;
	private String message;
	private Object data;
	
	public AjaxJson(){
		
	}
	
	public AjaxJson(int code){
		this.code = code;
		this.setMessage(AccessStatus.getDescription(code));
	}
	
    public AjaxJson(int code,String message){
        this.code = code;
        this.message = message;
    }

    public AjaxJson(Object object){
        this.data = object;
        this.code = AccessStatus.SERVER_SUCCESS;
        this.message = AccessStatus.getDescription(AccessStatus.SERVER_SUCCESS);
    }

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
