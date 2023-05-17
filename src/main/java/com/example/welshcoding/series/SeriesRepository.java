package com.example.welshcoding.series;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.example.welshcoding.domain.Series;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class SeriesRepository {
	
	private final EntityManager em;

	public List<Series> findSeriesAll(Long memberId) {
		String query = "select s from Series s INNER JOIN s.member m"
				+ " where m.memberId = :memberid";
		return em.createQuery(query, Series.class)
				 .setParameter("memberid", memberId)
				 .getResultList();
	}

}
