package com.example.welshcoding.signup;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.welshcoding.domain.Member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j 
@Controller
@RequiredArgsConstructor
public class SignupController {
	
	private final SignupService signupService;

	@PostMapping("/goSignup")
	public String signup(@RequestParam("userEmail") String userEmail,
			@RequestParam("userPw") String userPw,
			@RequestParam("userName") String userName,
			@RequestParam("userBirthyy") String userBirthyy,
			@RequestParam("userBirthmm") String userBirthmm,
			@RequestParam("userBirthdd") String userBirthdd,
			@RequestParam("gender") String gender,
			@RequestParam("userphone") String userphone
			) {
	    log.info("userEmail : " + userEmail);
	    log.info("userPw : " + userPw);
	    log.info("userName : " + userName);
	    log.info("userBirth : " + userBirthyy+"-"+ userBirthmm+"-"+ userBirthdd);
	    log.info("gender : " + gender);
	    log.info("userphone : " + userphone);
	    
	    String birth = userBirthyy+"-"+ userBirthmm+"-"+ userBirthdd;
	    
	    Member member = new Member();
	    member.setUserEmail(userEmail);
	    member.setUserPwd(userPw);
	    member.setUserName(userName);
	    member.setUserBirth(birth);
	    member.setUserGender(gender);
	    member.setUserPhone(userphone);
	    
	    
	    signupService.join(member);
	    
	    return "redirect:/gologin";
	}
	
	
	@PostMapping("dubcheck")
	@ResponseBody
	public String dubcheck(@RequestParam("email") String email) {
		String result ="not";
		
		if(signupService.dubcheck(email) == "possible") {
			result = "ok";
		}
		
		return result;
	}
	
	
}
