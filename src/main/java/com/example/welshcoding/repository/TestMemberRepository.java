package com.example.welshcoding.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

import com.example.welshcoding.domain.Board;
import com.example.welshcoding.domain.Member;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class TestMemberRepository {
	
	private final EntityManager em;
	
	public void save(Member member) {
		em.persist(member);
	}
	
	
	public Member findMember() {
		
		return em.createQuery("select i from Member i ", Member.class).getSingleResult();
	}
	
	public Member findTags(long testmemberid) {
		Member member = new Member();
		try {
			member = em.createQuery("select i from Member i where MEMBER_ID = :memberId", Member.class)
            .setParameter("memberId", testmemberid)
            .getSingleResult();
		} catch (Exception e) {
			System.out.println("결과없음");
		}
	    return member;
	}
	
	
	public Member findOne(Long mamberId) {
		return em.find(Member.class, mamberId);
	}
	
}
