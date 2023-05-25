package com.example.welshcoding.repository;


import javax.persistence.EntityManager;
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
        try {
            em.createQuery("select i from Member i where userEmail ='"
					+ email + "'", Member.class).getSingleResult();
            return "not";//있는아이디
        } catch (Exception e) {
//			e.printStackTrace();
            return "ok";//없는아이디
        }

    }
}
