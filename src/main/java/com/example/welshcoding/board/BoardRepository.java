package com.example.welshcoding.board;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.example.welshcoding.domain.Board;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class BoardRepository {
	private final EntityManager em;
	
	
	public List<Board> findAll() {
		
		return em.createQuery("select i from Board i ", Board.class).getResultList();
	}
	
	public void save(Board board) {
		em.persist(board);
	}
	
}
