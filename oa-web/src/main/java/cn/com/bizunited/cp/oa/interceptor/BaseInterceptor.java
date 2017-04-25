/**
 * @Description: 
 * @ClassName: cn.com.bizunited.cp.oa.interceptor.BaseInterceptor
 * @author: shenp
 * @date: 2017年1月22日 下午5:27:13 
 */
package cn.com.bizunited.cp.oa.interceptor;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cn.com.bizunited.cp.oa.commons.AccessStatus;
import cn.com.bizunited.cp.oa.vo.AjaxJson;
import net.sf.json.JSONObject;

/**
 * @Description: 
 * @ClassName: cn.com.bizunited.cp.oa.interceptor.BaseInterceptor
 * @author: shenp
 * @date: 2017年1月22日 下午5:27:13 
 *
 */
public abstract class BaseInterceptor extends HandlerInterceptorAdapter {

	private static final Logger logger = Logger.getLogger(BaseInterceptor.class);
	private ResourceBundle resource = ResourceBundle.getBundle("web");
	private final String ERROR_PAGE = resource.getString("error.page");
	
	/**
     * 统一返回错误json
     * @throws IOException 
     * @throws ServletException 
     */
    protected void sendError(HttpServletRequest request, HttpServletResponse response, int statusCode, boolean view) throws ServletException, IOException {
        logger.info("error is :" + statusCode);
        if(view){
        	sendErrorPage(request, response, AccessStatus.getDescription(statusCode));
        }else{
        	JSONObject responseJSONObject = JSONObject.fromObject(new AjaxJson(statusCode, AccessStatus.getDescription(statusCode)));
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            try (PrintWriter out = response.getWriter()) {
                out.append(responseJSONObject.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    
    /**
     * 错误页面跳转
     * @Title: sendErrorMessage 
     * @param request
     * @author: Paul Chen
     * @throws IOException 
     * @throws ServletException 
     * @date: 2016年8月29日 下午3:42:23
     */
    private void sendErrorPage(HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException{
    	request.setAttribute("message", message);
    	request.getRequestDispatcher(ERROR_PAGE).forward(request, response);
    }
	
}
