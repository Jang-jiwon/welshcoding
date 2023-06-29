package com.example.welshcoding.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;


public class LoginCheckInterceptor implements HandlerInterceptor {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String requestURI = request.getRequestURI();
		System.out.println("[interceptor]  : " + requestURI);
		HttpSession session = request.getSession(false);
		if( session == null || session.getAttribute("member") == null  ) {					//로그아웃상태
			if(!requestURI.startsWith("/login_out") && !requestURI.startsWith("/signup") ) {	//메인같은 페이지들
				System.out.println("[interceptor] logged out - wrong case : "+requestURI);
				response.sendRedirect("/login_out/gologin");
				return false;
			}	
		}else {																	//로그인상태
			if(!requestURI.endsWith("logout")&&
					( requestURI.startsWith("/login_out") || requestURI.startsWith("/signup") ) ) {	//로그인과 회원가입 , 로그아웃은 제외해야함
				System.out.println("[interceptor] logged out - wrong case : "+requestURI);
				response.sendRedirect("/mainBoard");
				return false;
			}
		}
		return true;

	}
}
