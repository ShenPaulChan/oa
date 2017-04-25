package cn.com.bizunited.cp.oa.interceptor;


import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.bizunited.cp.oa.utils.AdminLoginUtil;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Weston on 2016/8/17.
 */
public class ControllerInterceptor extends BaseInterceptor {
    private static final Logger logger = Logger.getLogger(ControllerInterceptor.class);

    /**
     * 所有跳转前的拦截
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String path = request.getServletPath();
        if("/myJsp".equals(path)){
            return true;
        }
    	HandlerMethod handlerMethod = (HandlerMethod) handler;
    	ResponseBody responseBody = handlerMethod.getMethodAnnotation(ResponseBody.class);
    	boolean view = false;
    	if(responseBody == null){//页面请求
    		view = true;
    	}
    	Enumeration<String> parameterNames = request.getParameterNames();
    	String paramsStr = "params >> ";
    	while (parameterNames.hasMoreElements()) {
			String key = (String) parameterNames.nextElement();
			paramsStr += key + ":" + request.getParameter(key) + ", ";
		}
    	logger.info("path:"+path);
    	logger.info(paramsStr);
    	logger.info("is view:"+view);


    	String currentName = AdminLoginUtil.getCurrentName();
    	System.out.println("currentName:"+currentName);
    	

        return true;
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

}
