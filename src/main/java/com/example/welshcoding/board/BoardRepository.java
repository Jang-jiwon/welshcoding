package com.example.welshcoding.board;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.example.welshcoding.domain.Board;
import com.example.welshcoding.domain.Member;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class BoardRepository {
	private final EntityManager em;
	
	
	public List<Board> findAll(long testmemberid) {
		
		return em.createQuery("select i from Board i where MEMBERID ='"+testmemberid+"'", Board.class).getResultList();
	}
	
	public void save(Board board) {
		em.persist(board);
	}
	
	public Board findOne(long boardId, long memberId) {
		System.out.println("개굴개굴개구리");
		
		Query query = em.createQuery("SELECT b FROM Board b WHERE b.boardId = :boardId AND b.member.memberId = :memberId  ", Board.class);
		query.setParameter("boardId", boardId);
		query.setParameter("memberId", memberId);
		Board board = (Board) query.getSingleResult();
		return board;
	}
	
}
