package com.example.welshcoding.testjiwon;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.welshcoding.domain.Board;
import com.example.welshcoding.domain.Member;
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
	
	public Temporary findOne2(long tempId , long memberId) {
		return testTemporaryRepository.findOne2(tempId, memberId);
	}
	
	public List<Temporary> findAll(long memberId) {
		return testTemporaryRepository.findAll(memberId);
	}
	
	@Transactional
	public void deleteById(Member member,Long temporaryId) {
		Temporary temp = findOne2(temporaryId,member.getMemberId());
		testTemporaryRepository.deleteTemp(temp);
	}
	
}
