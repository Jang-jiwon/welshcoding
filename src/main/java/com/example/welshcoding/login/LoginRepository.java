package com.example.welshcoding.login;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.example.welshcoding.domain.Member;
import com.example.welshcoding.domain.Series;

import lombok.RequiredArgsConstructor;


@Repository
@RequiredArgsConstructor
public class LoginRepository {
	
	private final EntityManager em;

	public Member findByUserEmail(String userEmail) {
		String query = "select m from Member m where m.userEmail = :userEmail";
		
		List<Member> memberList = em.createQuery(query, Member.class)
				 .setParameter("userEmail", userEmail)
				 .getResultList();
		
		if(memberList.size() == 0) {
			return null;
		} else {
			return memberList.get(0);
		}
		
	}

}
