package com.example.welshcoding.login;

import org.springframework.stereotype.Service;

import com.example.welshcoding.domain.Member;
import com.example.welshcoding.testeunho.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginService {

	private final LoginRepository loginRepository;
	
	public Member login(String userEmail, String userPwd) {
		Member member = loginRepository.findByUserEmail(userEmail);
		if(member != null && member.getUserPwd().equals(userPwd)) {
			return member;
		} else {
			return null;
		}
	}

}
