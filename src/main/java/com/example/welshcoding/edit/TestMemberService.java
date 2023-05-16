package com.example.welshcoding.edit;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.welshcoding.domain.Member;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TestMemberService {
	private final TestMemberRepository testMemberRepository;

	@Transactional
	public Long join(Member member) throws IllegalAccessException {
		testMemberRepository.save(member);
		return member.getMemberId();
	}
}
