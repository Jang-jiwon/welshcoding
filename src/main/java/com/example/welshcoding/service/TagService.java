package com.example.welshcoding.service;

import java.util.List;

import com.example.welshcoding.repository.TagRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.welshcoding.domain.Member;
import com.example.welshcoding.domain.Tags;

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
	

	public List<Tags> findTags2(long boardId , long memberId) {
		return tagRepository.findTags2(boardId,memberId);
	}
	
	@Transactional
	public void deleteById(long boardId , long memberId) {
		List<Tags>  oldtags = tagRepository.findTags2(boardId ,memberId);
		for(int i=0;i<oldtags.size();i++) {
			tagRepository.deleteTags(oldtags.get(i));
		}
		
	}
	
	@Transactional
	public void updateTags(List<Tags> tags ,long boardId , long memberId) throws IllegalAccessException {
//		List<Tags> oldtags = tagRepository.findTags2(boardId ,memberId);
//		tagRepository.deleteTags(oldtags);
		deleteById(boardId,memberId);
		for(int i=0;i<tags.size();i++) {
			save(tags.get(i));
		}
		
		
	}
}
