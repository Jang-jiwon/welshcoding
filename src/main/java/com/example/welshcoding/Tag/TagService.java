package com.example.welshcoding.Tag;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.welshcoding.domain.Board;
import com.example.welshcoding.domain.Member;
import com.example.welshcoding.domain.Tags;
import com.example.welshcoding.edit.TestMemberRepository;
import com.example.welshcoding.edit.TestMemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class TagService {
	private final TagRepository tagRepository;
	
	
	@Transactional
	public void save(Tags tags)throws IllegalAccessException {
		tagRepository.save(tags);
	}
	
	public List<Tags> findTags(Member member) {
		return tagRepository.findTags(member);
	}
}
