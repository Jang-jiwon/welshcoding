package com.example.welshcoding.repository;


import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.example.welshcoding.domain.Board;
import com.example.welshcoding.domain.Member;
import com.example.welshcoding.domain.Temporary;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class SignupRepository {
    private final EntityManager em;
    public void save(Member member) {
        em.persist(member);
    }

    public String findEmail(String email) {
        try { 							//있는아이디
            em.createQuery("select i from Member i where userEmail ='"
					+ email + "'", Member.class).getSingleResult();
            return "impossible";
        } catch (NoResultException e) {	//호출 시 결과가 하나도 없을 때 발생 - 없는아이디라는뜻
            return "possible";
        }
    }
}
