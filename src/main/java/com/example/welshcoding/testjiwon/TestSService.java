package com.example.welshcoding.testjiwon;

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
	
}
