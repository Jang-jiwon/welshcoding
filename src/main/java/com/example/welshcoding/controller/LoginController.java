package com.example.welshcoding.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.example.welshcoding.service.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.welshcoding.domain.LoginDTO;
import com.example.welshcoding.domain.Member;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LoginController {
	
	private final LoginService loginService;
	
	/*로그인 테스트를 위한 URL */
	@GetMapping("gologin")
	public String home() {
		return "login/login";
	}
	
	/*로그인 : 기존 지원님 방식으로 session 값 세팅했으므로 제껄로 대체해도 큰 문제 없을 겁니다.*/
	@PostMapping("login")
	public String login(@ModelAttribute LoginDTO loginDTO
			, Model model
			, HttpServletRequest request) throws IllegalAccessException {
		
		Member loginMember = loginService.login(loginDTO.getUserEmail(), loginDTO.getUserPwd());
		
		if(loginMember == null) {
			model.addAttribute("msg","로그인 실패");
			return "login/login";
		}
		
		HttpSession session = request.getSession();
		session.setAttribute("member", loginMember);
		
//		return "redirect:/mainBoard/"+loginMember.getMemberId();	
		return "redirect:/mainBoard";
	}
	
	/*로그아웃 : redirect 경로 수정 필요 */
	@GetMapping("logout")
	public String logout(HttpServletRequest request){
		HttpSession session = request.getSession(false);
		if(session != null) {
			System.out.println("세션 삭제");
			//session.removeAttribute("member");
			session.invalidate();
		}
		return "redirect:/";
	}
	
	@GetMapping("signup")
	public String signup() {
		return "signup/register";
	}
}
