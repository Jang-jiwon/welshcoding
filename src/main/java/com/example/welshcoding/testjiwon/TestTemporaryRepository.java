package com.example.welshcoding.testjiwon;

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
public class TestTemporaryRepository {
	private final EntityManager em;
	
	public void save(Temporary temporary) {
		em.persist(temporary);
	}
	
	public Temporary findOne(int tempId) {
		return em.find(Temporary.class, (long)tempId);
	}
	
	public Temporary findOne2(long tempId , long memberId) {
		
		Query query = em.createQuery("SELECT b FROM Temporary b WHERE b.temporaryId = :temporaryId AND b.member.memberId = :memberId  ", Temporary.class);
		query.setParameter("temporaryId", tempId);
		query.setParameter("memberId", memberId);
		Temporary temp = (Temporary)query.getSingleResult();
		return temp;
	}
	public List<Temporary> findAll(long memberId) {
		return em.createQuery("select i from Temporary i where MEMBERID ='"+memberId+"'", Temporary.class).getResultList();
	}
	
	@Transactional
	public void deleteTemp(Temporary temp) {
		 em.remove(temp);
	}
	
}
