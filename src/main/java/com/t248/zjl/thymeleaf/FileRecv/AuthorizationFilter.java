package com.t248.zjl.thymeleaf.FileRecv;


import javax.servlet.*;
import javax.servlet.FilterConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class AuthorizationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig)throws ServletException{

    }

    @Override
    public void destroy(){

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)throws IOException,ServletException{
        HttpServletRequest httpServletRequest= (HttpServletRequest) request;
        HttpServletResponse httpServletResponse= (HttpServletResponse) response;
        if(httpServletRequest.getSession().getAttribute("loginUser")==null){
            httpServletResponse.setContentType("text/html;charset=UTF-8");
            PrintWriter out=httpServletResponse.getWriter();
            out.println("<script>alert('请先进行登陆,再进行后续的操作!（Filter控制');location.href='"+
                    httpServletRequest.getContextPath()+"/login.jsp';</script>");
        }else {
            chain.doFilter(httpServletRequest,httpServletResponse);
        }
    }
}
