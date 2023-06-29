package com.example.welshcoding.interceptor;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

//@WebFilter("/*")
@Slf4j
public class ReloadFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		log.info("필터");
		HttpServletResponse httpResponse = (HttpServletResponse) response;
//		response.getWriter()
		chain.doFilter(request, response);
	}

}
