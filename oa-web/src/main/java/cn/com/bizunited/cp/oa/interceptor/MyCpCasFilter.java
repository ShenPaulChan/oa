package cn.com.bizunited.cp.oa.interceptor;

import cn.com.bizunited.cp.common.web.remote.service.AdminRemoteService;
import cn.com.bizunited.cp.common.web.vo.AdminVo;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.cas.CasFilter;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;

public class MyCpCasFilter
        extends CasFilter {
    private static final String DEFAULT_REDIRECT_URL = "admin/common/main.jhtml";
    @Resource(name = "adminRemoteServiceImpl")
    private AdminRemoteService adminRemoteService;
    private static final String DEFAULT_USER_INFO = "adminInfo";

    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response)
            throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String userName = (String) token.getPrincipal();
     
 	 /*if(){

 	 }*/


        AdminVo adminVo = adminRemoteService.getByUserName(userName);
        httpServletRequest.getSession().setAttribute("adminInfo", adminVo);

        redirectToSavedRequest(httpServletRequest, response, getSuccessUrl());
        return false;
        //return super.onLoginSuccess(token, subject, httpServletRequest, response);
    }

    public static void redirectToSavedRequest(ServletRequest request, ServletResponse response, String fallbackUrl)
            throws IOException {
        String successUrl = null;
        boolean contextRelative = true;
        SavedRequest savedRequest = WebUtils.getAndClearSavedRequest(request);
        if (savedRequest != null && savedRequest.getMethod().equalsIgnoreCase(AccessControlFilter.GET_METHOD)) {
            successUrl = savedRequest.getRequestUrl();
            contextRelative = false;
        }

        if (successUrl == null) {
            successUrl = fallbackUrl;
        }

        if (successUrl == null) {
            throw new IllegalStateException("Success URL not available via saved request or via the " +
                    "successUrlFallback method parameter. One of these must be non-null for " +
                    "issueSuccessRedirect() to work.");
        }

        WebUtils.issueRedirect(request, response, successUrl, null, contextRelative);
    }

    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException ae, ServletRequest request, ServletResponse response) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        if (token != null) {
            String userName = (String) token.getPrincipal();
            if (StringUtils.isNotBlank(userName)) {
                httpServletRequest.getSession().removeAttribute("adminInfo");
            }
        }
        return super.onLoginFailure(token, ae, httpServletRequest, response);
    }
}

/* Location:           E:\repo\repo\cn\com\bizunited\cp\common\commonweb\1.0\commonweb-1.0.jar
 * Qualified Name:     cn.com.bizunited.cp.common.web.shiro.CpCasFilter
 * Java Class Version: 7 (51.0)
 * JD-Core Version:    0.7.1
 */