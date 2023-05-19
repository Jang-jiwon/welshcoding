package com.example.welshcoding.testeunho;

import com.example.welshcoding.domain.Member;
import com.example.welshcoding.domain.Sns;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

//    private MemberDTO convertToDTO(Member member) {
//        MemberDTO memberDTO = new MemberDTO();
//        memberDTO.setUserName(member.getUserName());
//        memberDTO.setUserBio(member.getUserBio());
//        memberDTO.setSns(member.getSns()); // Set SNS data
//        memberDTO.setVelogPageName(member.getVelogPageName()); // Set Velog_page_name data
//        // Set other properties as needed
//        return memberDTO;
//    }

    public Member getMemberById(Long memberId) {
        // memberId를 사용하여 회원 정보를 조회하는 로직을 작성하세요.
        // 예시로 간단히 memberId에 해당하는 회원을 데이터베이스에서 조회하도록 구현했습니다.
       return memberRepository.findById(memberId);
    }


    @Transactional
    public void editMemberById(Long memberId, EditFormDTO form) {
        Member member = memberRepository.findById(memberId);
        member.setUserName(form.getUserName());
        member.setUserBio(form.getUserBio());
    }

    @Transactional
    public void edit2MemberById(Long memberId, String velogPageName) {
        Member member = memberRepository.findById(memberId);
        member.setVelogPageName(velogPageName);
    }

//    @Transactional
//    public void edit3MemberById(Long memberId, String email, String Github, String Twitter,
//                                String Facebook, String UserHomepage) {
//        Member member = memberRepository.findById(memberId);
//        Sns sns = memberRepository.findById(memberId).getSns();
//
//        member.setUserEmail(email);
//
//        sns.setUserGithub(Github);
//        sns.setUserTwitter(Twitter);
//        sns.setUserFacebook(Facebook);
//        sns.setUserHomepage(UserHomepage);
//    }

    @Transactional
    public void edit3MemberById(Long memberId, EditSnsDTO editSnsDTO) {

        Member member = memberRepository.findById(memberId);
//        Sns sns = memberRepository.findById(memberId).getSns();

        member.setUserEmail(editSnsDTO.getUserEmail());
        member.getSns().setUserGithub(editSnsDTO.getUserGithub());
        member.getSns().setUserHomepage(editSnsDTO.getUserHomepage());
        member.getSns().setUserTwitter(editSnsDTO.getUserTwitter());
        member.getSns().setUserFacebook(editSnsDTO.getUserFacebook());
    }

    @Transactional
    public void deleteById(Long memberId) {
        memberRepository.deleteById(memberId);
    }
}
