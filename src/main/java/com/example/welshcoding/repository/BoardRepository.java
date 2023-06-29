package com.example.welshcoding.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.welshcoding.domain.Board;
import com.example.welshcoding.domain.Member;
import com.example.welshcoding.domain.Series;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class BoardRepository {
    private final EntityManager em;


    public List<Board> findAll(long testmemberid) {

        return em.createQuery("select i from Board i where MEMBERID ='"
				+ testmemberid + "'", Board.class).getResultList();
    }

    public void save(Board board) {
        em.persist(board);
    }

    public Board findOne(long boardId, long memberId) {
        Query query = em.createQuery("SELECT b FROM Board b WHERE b.boardId = :boardId AND b.member.memberId = :memberId  ", Board.class);
        query.setParameter("boardId", boardId);
        query.setParameter("memberId", memberId);
        Board board = (Board) query.getSingleResult();
        return board;
    }


    public List<Board> search(long memberId, String inputSearch) {
        Query query = em.createQuery("SELECT b FROM Board b WHERE (LOWER(b.boardCont) LIKE LOWER(:boardCont) OR LOWER(b.boardTitle) LIKE LOWER(:boardCont)) AND b.member.memberId = :memberId ", Board.class);

        query.setParameter("boardCont", "%" + inputSearch + "%");
        query.setParameter("memberId", memberId);
        List<Board> results = query.getResultList();
        return results;
    }

    @Transactional
    public void deleteBoard(Board board) {
        em.remove(board);
    }
}
