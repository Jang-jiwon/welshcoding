package com.example.welshcoding.introduce.service;

import com.example.welshcoding.domain.Introduce;
import com.example.welshcoding.domain.Member;
import com.example.welshcoding.introduce.repository.IntroduceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class IntroduceService {

    private final IntroduceRepository introduceRepository;

    @Transactional
    public void saveIntroduce(String content, Member member) {
        Introduce introduce = new Introduce();
        introduce.setContent(content);
        introduce.setMember(member);

        introduceRepository.saveIntroduce(introduce);
    }

    public Introduce findById(Long memberId) {
        return introduceRepository.findById(memberId);
    }

}

