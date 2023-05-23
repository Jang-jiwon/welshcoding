package com.example.welshcoding.service;

import java.util.List;

import com.example.welshcoding.repository.TemporaryRepository;
import org.springframework.stereotype.Service;

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
