package com.example.welshcoding.edit;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.welshcoding.domain.Board;
import com.example.welshcoding.domain.Member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class TestMemberService {
	private final TestMemberRepository testMemberRepository;

	@Transactional
	public Long join(Member member) throws IllegalAccessException {
		testMemberRepository.save(member);
		return member.getMemberId();
	}
	
	@Transactional
	public void updateSrc(String imgsrc,long memberId) {
		Member member = testMemberRepository.findOne(memberId);
		member.setProfileImg(imgsrc);
	}
	
	public Member findMember() {
		return testMemberRepository.findMember();
	}
	
	public String findTags(long testmemberid) {
		String tags = testMemberRepository.findTags(testmemberid).getTagsString();
		return tags;
	}
	
	@Transactional
	public Member addTags(long memberId,String tagList) {
		String newtags = "";
		Member findmem = testMemberRepository.findOne(memberId);
		
		if (findmem.getTags()==null) {		//기존 태그가 비어잇을경우
			newtags = tagList;
		}else {								//기존 태그가 있을경우
			String[] newtagsArray = tagList.split(",");//11
			for(int i=0;i<newtagsArray.length;i++) {
				if(!findmem.getTags().contains(newtagsArray[i])) {//11,22
					
					if(newtags == "") {
						newtags += newtagsArray[i];
					}else {
						newtags += ","+newtagsArray[i];
					}
					
				}else {
					log.info("8118newtagsArray");
				}
				
			}
			newtags = newtags + "," +findmem.getTags();
		}
		findmem.setTagsString(newtags);
		return findmem;
	}
}
