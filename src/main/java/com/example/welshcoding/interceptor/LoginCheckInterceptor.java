package com.example.welshcoding.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;


public class LoginCheckInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)throws Exception {
		String requestURI = request.getRequestURI();
		HttpSession session = request.getSession(false);
		if( session == null || session.getAttribute("member") == null  ) {
			System.out.println("[interceptor] logged out : "+requestURI);
			response.sendRedirect("/login_out/gologin");
			return false;
		}
		return true;
	}
}
