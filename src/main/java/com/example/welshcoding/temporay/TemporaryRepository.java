package com.example.welshcoding.temporay;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import com.example.welshcoding.domain.Board;
import com.example.welshcoding.domain.Temporary;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class TemporaryRepository {
	
	private final EntityManager em;
	
	public void save(Temporary temporary) {
		// 처음에 item이 없으면 id가 null값이기 때문이다.
		
			em.persist(temporary);
		} 
	public List<Temporary> findAll(long testmemberid) {
		
		return em.createQuery("select i from Board i where MEMBERID ='"+testmemberid+"'", Temporary.class).getResultList();
	}
	
	public Temporary findOne(long temporaryId, long memberId) {
		System.out.println("개굴개굴개구리");
		
		Query query = em.createQuery("SELECT b FROM temporary b WHERE b.temporaryId = :temporaryId AND b.member.memberId = :memberId  ", Temporary.class);
		query.setParameter("temporaryId", temporaryId);
		query.setParameter("memberId", memberId);
		Temporary temporary = (Temporary) query.getSingleResult();
		return temporary;
	}
	
	@Transactional
	public void deleteTemporary(Temporary temporary) {
		 em.remove(temporary);
	}

}
