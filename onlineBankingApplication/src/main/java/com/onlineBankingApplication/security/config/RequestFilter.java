package com.onlineBankingApplication.security.config;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RequestFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest =   (HttpServletRequest)request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		Enumeration<String> headerNames = httpRequest.getHeaderNames();
		while(headerNames.hasMoreElements()){
			System.out.println("Get From Header "+headerNames.nextElement());
		}
		
		httpResponse.setHeader("Access-Control-Allow-Origin","http://localhost:4200");
		httpResponse.setHeader("Access-Control-Allow-Methods","POST,PUT,GET,OPTIONS,DELETE");
		httpResponse.setHeader("Access-Control-Allow-Headers","x-requested-with");
		httpResponse.setHeader("Access-Control-Max-Age","3600");
		httpResponse.setHeader("Access-Control-Allow-Credentials","true");
		httpResponse.setHeader("Content-Type","application/json");
		System.out.println(IOUtils.toString(httpRequest.getReader()));
		if(!(httpRequest.getMethod().equalsIgnoreCase("OPTIONS"))){
			try{
				chain.doFilter(httpRequest, httpResponse);
			}catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			System.out.println("Pre-flight");
			httpResponse.setHeader("Access-Control-Allow-Methods","POST,GET,DELETE");
			httpResponse.setHeader("Access-Control-Allow-Headers","authorization,content-type,Content-Type:application/json,"
					+ "access-control-request-headers,access-control-request-method,"
					+ "accept,origin,authorization,x-requested-with");
			httpResponse.setHeader("Access-Control-Max-Age","3600");
			httpResponse.setStatus(HttpServletResponse.SC_OK);
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
