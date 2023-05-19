package com.example.welshcoding.testjiwon;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.welshcoding.domain.Member;
import com.example.welshcoding.domain.Series;
import com.example.welshcoding.edit.TestMemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TestSService {
	private final TestSRepository testSRepository;
	
	@Transactional
	public Long save(Series series) throws IllegalAccessException {
		testSRepository.save(series);
		return series.getSeriesId();
	}
	
	public List<Series> findAll (long id) {
		return testSRepository.findAll(id);
	}
	
	public Series isIn(String memberId) {
		return testSRepository.isIn(memberId);
	}
}
