package com.example.welshcoding.testjiwon;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

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
	
}
