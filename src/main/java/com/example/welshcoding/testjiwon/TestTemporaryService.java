package com.example.welshcoding.testjiwon;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.welshcoding.domain.Temporary;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TestTemporaryService {
	private final TestTemporaryRepository testTemporaryRepository;

	@Transactional
	public void save(Temporary temporary) throws IllegalAccessException {
		testTemporaryRepository.save(temporary);
	}
	
	public Temporary findOne(int tempId) {
		return testTemporaryRepository.findOne(tempId);
	}
}
