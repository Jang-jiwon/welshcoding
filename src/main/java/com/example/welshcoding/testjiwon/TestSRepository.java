package com.example.welshcoding.testjiwon;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.example.welshcoding.domain.Board;
import com.example.welshcoding.domain.Member;
import com.example.welshcoding.domain.Series;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class TestSRepository {
	private final EntityManager em;
	public void save(Series series) {
		em.persist(series);
	}
	
	public List<Series> findAll(long memberId) {
			return em.createQuery("select i from Series i where MEMBERID =:memberId", Series.class)
					.setParameter("memberId", memberId)
					.getResultList();
		}
	
	public Series isIn(String seriesName) {
		Series series = null;
		try {
			series = em.createQuery("select i from Series i where seriesName = :seriesName", Series.class)
			        .setParameter("seriesName", seriesName)
			        .getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return series;
	}
}
