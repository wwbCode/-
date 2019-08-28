package com.jinyafu.thirdpart.filter;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 网关过滤器
 * 
 * @version 2019年8月28日上午11:40:12
 * @author Ly
 */
@Component
@WebFilter(urlPatterns = {"/*"}, filterName = "ocrsFilter")
public class ServerFilter implements Filter {
	private static Logger log = LoggerFactory.getLogger(ServerFilter.class);

	@Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }
	
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
    	HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
    	// 跨域配置
        String curOrigin = request.getHeader("Origin");
        log.info("方式={}，请求源={}", request.getMethod(), curOrigin);
      //解决跨域的问题
        response.setHeader("Access-Control-Allow-Origin","*");
        //response.setHeader("Access-Control-Allow-Origin","http://localhost:6603");
        response.setHeader("Access-Control-Allow-Credentials","true");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type,Content-Length, Authorization, Accept,X-Requested-With,X-App-Id, X-Token");
        response.setHeader("Access-Control-Allow-Methods","PUT,POST,GET,DELETE,OPTIONS");
        response.setHeader("Access-Control-Max-Age", "3600");
		
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
