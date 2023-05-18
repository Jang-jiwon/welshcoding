package com.example.welshcoding.edit;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.welshcoding.domain.Board;
import com.example.welshcoding.domain.Member;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TestMemberService {
	private final TestMemberRepository testMemberRepository;

	@Transactional
	public Long join(Member member) throws IllegalAccessException {
		testMemberRepository.save(member);
		return member.getMemberId();
	}
	
	public Member findMember() {
		return testMemberRepository.findMember();
	}
	
	public String findTags(long testmemberid) {
		String tags = testMemberRepository.findTags(testmemberid).getTags();
		return tags;
	}
	
	@Transactional
	public Member addTags(long memberId,String tagList) {
		String newtags = "";
		Member findmem = testMemberRepository.findOne(memberId);
		
		if (findmem.getTags()==null) {		//기존 태그가 비어잇을경우
			newtags = tagList;
		}else {								//기존 태그가 있을경우
			String[] newtagsArray = tagList.split(",");//11,22
			for(int i=0;i<newtagsArray.length;i++) {
				if(!findmem.getTags().contains(newtagsArray[i])) {//11
					if(newtags == "") {
						newtags += newtagsArray[i];
					}else {
						newtags += ","+newtagsArray[i];
					}
				}
				
			}
			newtags = newtags + "," +findmem.getTags();
		}
		findmem.setTags(newtags);
		return findmem;
	}
}
