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
		System.out.println("[인증 체크 인터셉터 실행] : " + requestURI);
		HttpSession session = request.getSession(false);
		if( session == null || 
				session.getAttribute("member") == null) {
			System.out.println("[미인증 사용자 요청]");
			response.sendRedirect("/login?redirectURL="+requestURI);
			return false;
		}
		return true; // false 로 전환 시, 더 이상 인터셉터를 진행하지 않는다.
	}
}
