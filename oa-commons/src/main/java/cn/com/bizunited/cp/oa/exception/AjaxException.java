package cn.com.bizunited.cp.oa.exception;

/**
 * Created by Weston on 2016/8/21.
 */
public class AjaxException extends RuntimeException {
    /**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = -1447410501070792327L;
	protected int httpStatus = 0;

    public AjaxException(){

    }

    public AjaxException(int httpStatus) {
        this.httpStatus = httpStatus;
    }

    public int getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(int httpStatus) {
        this.httpStatus = httpStatus;
    }
}
