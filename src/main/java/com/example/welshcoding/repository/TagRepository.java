package com.example.welshcoding.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.welshcoding.domain.Board;
import com.example.welshcoding.domain.Member;
import com.example.welshcoding.domain.Tags;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class TagRepository {
	private final EntityManager em;

	public void save(Tags tags) {
		em.persist(tags);
	}
	
	public List<Tags> findTags(Member member) {
		Query query = em.createQuery("SELECT b FROM Tags b WHERE b.member.memberId = :memberId  ", Tags.class);
		query.setParameter("memberId", member.getMemberId());
		List<Tags> tags = query.getResultList();
		return tags;
	}
	
	public List<Tags> findTags2(long boardId , long memberId) {
		Query query = em.createQuery("SELECT b FROM Tags b WHERE b.member.memberId = :memberId  AND b.board.boardId = :boardId", Tags.class);
		query.setParameter("memberId", memberId);
		query.setParameter("boardId", boardId);
		List<Tags> tags = query.getResultList();
		return tags;
	}
	
	@Transactional
	public void deleteTags(Tags tags) {
		 em.remove(tags);
	}
	
}
