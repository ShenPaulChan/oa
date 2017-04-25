/**
 * @Description: 
 * @ClassName: cn.com.bizunited.oa.controller.BaseController
 * @author: shenp
 * @date: 2017年1月19日 下午4:34:33 
 */
package cn.com.bizunited.cp.oa.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.ModelAndView;

import cn.com.bizunited.cp.oa.commons.AccessStatus;
import cn.com.bizunited.cp.oa.exception.AjaxException;
import cn.com.bizunited.cp.oa.exception.ErrorPageException;
import cn.com.bizunited.cp.oa.exception.ServerException;
import cn.com.bizunited.cp.oa.vo.AjaxJson;
import net.sf.json.JSONObject;

/**
 * @Description: 
 * @ClassName: cn.com.bizunited.oa.controller.BaseController
 * @author: shenp
 * @date: 2017年1月19日 下午4:34:33 
 *
 */
public class BaseController {

	private static final Logger logger = Logger.getLogger(BaseController.class);

    private static final String ERROR_PAGE = "error";


    /**
     * 异常公共接受
     *
     * @param ex
     * @param response
     * @Title: exceptionExecute
     * @author: TRivers.chen(Administrator)
     * @date: 2016年4月28日 上午10:17:42
     */
    @ExceptionHandler
    public ModelAndView exceptionExecute(Exception ex, HttpServletResponse response, Object handler) {
        if (ex instanceof ServerException) {
            logger.info("throw exception errorCode is the:" + ((ServerException) ex).getHttpStatus());
            logger.info("throw exception message :" + ((ServerException) ex).getMessages());
            ex.printStackTrace();
            sendError(response, ((ServerException) ex).getHttpStatus(), ((ServerException) ex).getMessages());
        } else if (ex instanceof AjaxException) {
            logger.info("throw exception errorCode is the:" + ((AjaxException) ex).getHttpStatus());
            logger.info("throw exception message :" + ((AjaxException) ex).getMessage());
            ex.printStackTrace();
            sendError(response, ((AjaxException) ex).getHttpStatus());
        }
        else if(ex instanceof ErrorPageException){
        	logger.info("throw exception errorCode is the:" + ((ErrorPageException) ex).getCode());
        	logger.info("throw exception message :" + ((ErrorPageException) ex).getMessage());
        	ex.printStackTrace();
        	ModelAndView modelAndView = new ModelAndView(ERROR_PAGE);
        	modelAndView.addObject("code", ((ErrorPageException) ex).getCode());
        	modelAndView.addObject("message", ex.getMessage());
        	return modelAndView;
        }
        else {
        	ex.printStackTrace();
            sendError(response, AccessStatus.SERVER_GENERIC_ERROR);
        }
        return null;
    }

    /**
     * 统一返回错误json,只传入错误编码
     */
    protected void sendError(HttpServletResponse response, int statusCode) {
        logger.info("error is :" + statusCode);
        JSONObject responseJSONObject = JSONObject.fromObject(new AjaxJson(statusCode, AccessStatus.getDescription(statusCode)));
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        try (PrintWriter out = response.getWriter()) {
            out.append(responseJSONObject.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 统一返回错误json, 传入错误编码和错误描述
     */
    protected void sendError(HttpServletResponse response, int statusCode, String message) {
        logger.info("error is :" + statusCode);
        JSONObject responseJSONObject = JSONObject.fromObject(new AjaxJson(statusCode, message));
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        try (PrintWriter out = response.getWriter()) {
            out.append(responseJSONObject.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 验证是否为空
     */
    @SuppressWarnings("rawtypes")
	protected static void checkParameters(Object... objects) throws ServerException {
        for (Object object : objects) {
            if (object == null) {
                throw new ServerException(AccessStatus.INVALID_REQUEST_PARAMETER);
            } else {
                if (object instanceof String) {
                    if (org.springframework.util.StringUtils.isEmpty(object)) {
                        throw new ServerException(AccessStatus.INVALID_REQUEST_PARAMETER);
                    }
                } else if (object instanceof List) {
                    if (org.springframework.util.CollectionUtils.isEmpty((List) object)) {
                        throw new ServerException(AccessStatus.INVALID_REQUEST_PARAMETER);
                    }
                }
            }
        }
    }
    
    /**
     * 获取当前登录人信息	
     * @return
     */
    public String getCurrentUsername(){
    	
         Subject subject = SecurityUtils.getSubject();
         if (subject != null) {
           String principal = (String)subject.getPrincipal();
           if (principal != null) {
             return principal;
           }
         }
         return null;
   }
    
    /**
     * 获取当前登录人id
     * @return
     */
    public Integer getCurrentUserId(){
    	
        Subject subject = SecurityUtils.getSubject();
        if (subject != null) {
        	PrincipalCollection principals = subject.getPrincipals();
            if (!CollectionUtils.isEmpty(principals)) {
         	   
         	   for (Object p : principals.asList()) {
         		   if ((p instanceof Map)) {
         			   
         			   return Integer.valueOf((String)((Map)p).get("id"));
         		   }
         	   }
            }
        }
        return null;
    }
   /**
    * 对前台的时间格式的处理必须是（yyyy-MM-dd HH:mm:ss）
    * @param binder
    */
   @InitBinder
   public void binder(WebDataBinder binder){
	   
	   SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	   dateFormat.setLenient(false);  
	   binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));   //true:允许输入空值，false:不能为空值
   }

   public void setDownLoadHeader(HttpServletResponse response,String filenameStr){
       response.setContentType("application/octet-stream;charset=ISO8859-1");
       String fileName = null;
       try {
           fileName = new String(filenameStr.getBytes(),"ISO8859-1");
       } catch (UnsupportedEncodingException e) {
           e.printStackTrace();
       }
       response.setHeader("Content-disposition", "attachment; filename=" +fileName);
       response.addHeader("Pargam", "no-cache");
       response.addHeader("Cache-Control", "no-cache");
   }
}
