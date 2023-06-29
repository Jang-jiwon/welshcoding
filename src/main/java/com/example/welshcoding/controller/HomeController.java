 package com.example.welshcoding.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.welshcoding.domain.Member;
import com.example.welshcoding.service.TestMemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
public class HomeController {


	private final TestMemberService testMemberService;
	
	@RequestMapping("/")
	public String home( HttpSession session) throws IllegalAccessException {
		
		return "redirect:/login_out/gologin";	
	}
}
