package com.t248.zjl.thymeleaf.FileRecv;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class AuthorizationInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler)throws Exception{
        if(request.getSession().getAttribute("loginUser")==null){
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out=response.getWriter();
            out.println("<script>alert('请先进行登陆,再进行后续的操作!（Interceptor控制');location.href='"+
                    request.getContextPath()+"/login.jsp';</script>");
            return false;
        }else{
            return true;
        }
    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception{

    }
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,Exception ex)throws Exception{

    }
}
