package com.example.welshcoding.temporay;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.welshcoding.domain.Board;
import com.example.welshcoding.domain.Temporary;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TemporaryService {
	
	private final TemporaryRepository temporaryRepository;
	
	public List<Temporary> findTemporarys(long testmemberid) {
		return temporaryRepository.findAll(testmemberid);
	}

	public Temporary findOne(Long temporaryId, Long memberId) {
		return temporaryRepository.findOne(temporaryId,	memberId);
	}

	

	

}
