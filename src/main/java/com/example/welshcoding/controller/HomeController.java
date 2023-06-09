 package com.example.welshcoding.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;

import com.example.welshcoding.domain.Member;
import com.example.welshcoding.service.TestMemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
public class HomeController {


	private final TestMemberService testMemberService;
	
//	@RequestMapping("login")
	public String home( HttpSession session) throws IllegalAccessException {
		Member member = new Member();
		member.setUserEmail("test");
		
		Long id = testMemberService.join(member);
		
		session.setAttribute("member", member);
		return "redirect:/mainBoard/"+id;	
	}
}
