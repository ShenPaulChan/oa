package cn.com.bizunited.cp.oa.exception;

/**
 * Created by Weston on 2016/8/21.
 */
public class HttpException extends RuntimeException {
    /**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 2405740060369251369L;
	protected int httpStatus = 0;

    public HttpException(){

    }

    public HttpException(int httpStatus) {
        this.httpStatus = httpStatus;
    }

    public int getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(int httpStatus) {
        this.httpStatus = httpStatus;
    }
}
