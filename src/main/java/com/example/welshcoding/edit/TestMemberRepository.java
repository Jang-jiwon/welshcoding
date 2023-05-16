package com.example.welshcoding.edit;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.example.welshcoding.domain.Member;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class TestMemberRepository {
	private final EntityManager em;
	public void save(Member member) {
		em.persist(member);
	}
}
