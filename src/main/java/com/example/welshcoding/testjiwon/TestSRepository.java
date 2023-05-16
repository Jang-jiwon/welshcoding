package com.example.welshcoding.testjiwon;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.example.welshcoding.domain.Series;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class TestSRepository {
	private final EntityManager em;
	public void save(Series series) {
		em.persist(series);
	}
}
